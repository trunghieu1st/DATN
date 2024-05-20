package com.example.techstore.service.impl;

import com.example.techstore.constant.ErrorMessage;
import com.example.techstore.constant.MessageConstant;
import com.example.techstore.constant.SortByDataConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.pagination.PagingMeta;
import com.example.techstore.domain.dto.request.CartCreateDto;
import com.example.techstore.domain.dto.request.CartUpdateDto;
import com.example.techstore.domain.dto.response.CartDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.entity.Cart;
import com.example.techstore.domain.entity.ProductOption;
import com.example.techstore.domain.entity.User;
import com.example.techstore.domain.mapper.CartMapper;
import com.example.techstore.exception.ForbiddenException;
import com.example.techstore.exception.NotFoundException;
import com.example.techstore.repository.CartRepository;
import com.example.techstore.repository.ProductOptionRepository;
import com.example.techstore.repository.UserRepository;
import com.example.techstore.service.CartService;
import com.example.techstore.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductOptionRepository productOptionRepository;
    private final CartMapper cartMapper;

    @Override
    public PaginationResponseDto<CartDto> getAll(String userId, PaginationFullRequestDto paginationFullRequestDto) {
        Pageable pageable = PaginationUtil
                .buildPageable(paginationFullRequestDto, SortByDataConstant.CART);
        Page<Cart> cartPage = cartRepository.getAllByUser(userId, pageable);
        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.CART, cartPage);

        List<CartDto> cartDtos = cartMapper.mapCartsToCartDtos(cartPage.getContent());

        return new PaginationResponseDto<>(meta, cartDtos);
    }

    @Override
    public int getNumberItem(String userId) {
        return cartRepository.getNumberItem(userId);
    }

    @Override
    public CartDto create(String userId, CartCreateDto cartCreateDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{userId}));
        ProductOption productOption = productOptionRepository.findById(cartCreateDto.getProductOptionId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ProductOption.ERR_NOT_FOUND_ID, new String[]{cartCreateDto.getProductOptionId()}));

        if (productOption.getQuantity() == 0) {
            throw new NotFoundException(ErrorMessage.ProductOption.ERR_OUT_OF_STOCK);
        }
        if (cartCreateDto.getQuantity() > productOption.getQuantity()) {
            throw new NotFoundException(ErrorMessage.Cart.ERR_QUANTITY_EXCEEDED);
        }

        Cart cart = cartRepository.findByUserIdAndProductOptionId(userId, cartCreateDto.getProductOptionId());
        if (cart != null) {
            int newQuantity = cart.getQuantity() + cartCreateDto.getQuantity();
            cart.setQuantity(newQuantity > productOption.getQuantity() ? productOption.getQuantity() : newQuantity);
        } else {
            cart = cartMapper.mapCartCreateDtoToCart(cartCreateDto);
            cart.setUser(user);
            cart.setProductOption(productOption);
        }
        return cartMapper.mapCartToCartDto(cartRepository.save(cart));
    }

    @Override
    public CartDto updateById(String userId, String id, CartUpdateDto cartUpdateDto) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Cart.ERR_NOT_FOUND_ID, new String[]{id}));
        if (!cart.getCreatedBy().equals(userId)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_UPDATE_DELETE);
        }

        if (cartUpdateDto.getQuantity() > cart.getProductOption().getQuantity()) {
            throw new NotFoundException(ErrorMessage.Cart.ERR_QUANTITY_EXCEEDED);
        }
        cart.setQuantity(cartUpdateDto.getQuantity());

        return cartMapper.mapCartToCartDto(cartRepository.save(cart));
    }

    @Override
    public CommonResponseDto deleteById(String userId, String id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Cart.ERR_NOT_FOUND_ID, new String[]{id}));
        if (!cart.getCreatedBy().equals(userId)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_UPDATE_DELETE);
        }
        cartRepository.delete(cart);
        return new CommonResponseDto(true, MessageConstant.DELETE_CART_ITEM_SUCCESSFULLY);
    }
}
