package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.CartCreateDto;
import com.example.techstore.domain.dto.response.CartDto;
import com.example.techstore.domain.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CartMapper {
    Cart mapCartCreateDtoToCart(CartCreateDto cartCreateDto);

    @Mappings({
            @Mapping(target = "productOptionDto.productId", source = "productOption.product.id"),
            @Mapping(target = "productOptionDto.productName", source = "productOption.product.name"),
            @Mapping(target = "productOptionDto", source = "productOption"),
    })
    CartDto mapCartToCartDto(Cart cart);

    List<CartDto> mapCartsToCartDtos(List<Cart> carts);

}
