package com.example.techstore.service.impl;

import com.example.techstore.constant.ErrorMessage;
import com.example.techstore.constant.MessageConstant;
import com.example.techstore.constant.SortByDataConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.pagination.PagingMeta;
import com.example.techstore.domain.dto.request.UserDiscountCreateDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.dto.response.UserDiscountDto;
import com.example.techstore.domain.entity.DiscountCode;
import com.example.techstore.domain.entity.User;
import com.example.techstore.domain.entity.UserDiscount;
import com.example.techstore.domain.mapper.UserDiscountMapper;
import com.example.techstore.exception.AlreadyExistException;
import com.example.techstore.exception.NotFoundException;
import com.example.techstore.repository.DiscountCodeRepository;
import com.example.techstore.repository.UserDiscountRepository;
import com.example.techstore.repository.UserRepository;
import com.example.techstore.service.UserDiscountService;
import com.example.techstore.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDiscountServiceImpl implements UserDiscountService {
    private final UserDiscountRepository userDiscountRepository;
    private final UserDiscountMapper userDiscountMapper;
    private final DiscountCodeRepository discountCodeRepository;
    private final UserRepository userRepository;

    @Override
    public UserDiscountDto getById(String id) {
        UserDiscount userDiscount = userDiscountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.UserDiscount.ERR_NOT_FOUND_ID, new String[]{id}));
        return userDiscountMapper.mapUserDiscountToUserDiscountDto(userDiscount);
    }

    @Override
    public PaginationResponseDto<UserDiscountDto> getAll(Boolean status, PaginationFullRequestDto paginationFullRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationFullRequestDto, SortByDataConstant.USER_DISCOUNT);
        Page<UserDiscount> userDiscountPage = userDiscountRepository.getAll(status, pageable);
        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.USER_DISCOUNT, userDiscountPage);

        List<UserDiscountDto> userDiscountDtoList = userDiscountMapper.mapUserDiscountToUserDiscountDto(userDiscountPage.getContent());
        return new PaginationResponseDto<>(meta, userDiscountDtoList);
    }

    @Override
    public PaginationResponseDto<UserDiscountDto> getAllByUserId(String userId, Boolean type, Boolean status, PaginationFullRequestDto paginationFullRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationFullRequestDto, SortByDataConstant.USER_DISCOUNT);
        Page<UserDiscount> userDiscountPage = userDiscountRepository.getAllByUserId(userId, type, status, pageable);
        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.USER_DISCOUNT, userDiscountPage);

        List<UserDiscountDto> userDiscountDtoList = userDiscountMapper.mapUserDiscountToUserDiscountDto(userDiscountPage.getContent());
        return new PaginationResponseDto<>(meta, userDiscountDtoList);
    }

    @Override
    public UserDiscountDto create(String userId, UserDiscountCreateDto createDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{userId}));
        DiscountCode discountCode = discountCodeRepository.findById(createDto.getDiscountCodeId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.DiscountCode.ERR_NOT_FOUND_ID, new String[]{createDto.getDiscountCodeId()}));
        if (userDiscountRepository.existsByUserAndDiscountCode(user, discountCode)) {
            throw new AlreadyExistException(ErrorMessage.UserDiscount.ERR_ALREADY_EXIST);
        }
        if (discountCode.getQuantity() <= 0) {
            throw new NotFoundException(ErrorMessage.DiscountCode.EXPIRED_DISCOUNT_CODE);
        }
        UserDiscount userDiscount = userDiscountMapper.mapUserDiscountCreateDtoToUserDiscount(createDto);
        userDiscount.setUser(user);
        userDiscount.setDiscountCode(discountCode);
        userDiscount.setStatus(true);
        discountCode.setQuantity(discountCode.getQuantity() - 1);
        return userDiscountMapper.mapUserDiscountToUserDiscountDto(userDiscountRepository.save(userDiscount));
    }

    @Override
    public UserDiscountDto update(String id) {
        UserDiscount userDiscount = userDiscountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.UserDiscount.ERR_NOT_FOUND_ID, new String[]{id}));
        userDiscount.setAppliedDate(LocalDateTime.now());
        userDiscount.setStatus(false);
        return userDiscountMapper.mapUserDiscountToUserDiscountDto(userDiscountRepository.save(userDiscount));
    }

    @Override
    public UserDiscountDto addDiscountCode(String userId, String code) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{userId}));
        DiscountCode discountCode = discountCodeRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.DiscountCode.ERR_NOT_FOUND_CODE, new String[]{code}));
        if (userDiscountRepository.existsByUserAndDiscountCode(user, discountCode)) {
            throw new AlreadyExistException(ErrorMessage.DiscountCode.ERR_ALREADY_USED);
        }
        if (discountCode.getQuantity() <= 0) {
            throw new NotFoundException(ErrorMessage.DiscountCode.EXPIRED_DISCOUNT_CODE);
        }
        UserDiscount userDiscount = new UserDiscount();
        userDiscount.setUser(user);
        userDiscount.setDiscountCode(discountCode);
        userDiscount.setStatus(true);
        discountCode.setQuantity(discountCode.getQuantity() - 1);
        return userDiscountMapper.mapUserDiscountToUserDiscountDto(userDiscountRepository.save(userDiscount));
    }

    @Override
    public CommonResponseDto deleteById(String id) {
        UserDiscount userDiscount = userDiscountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.UserDiscount.ERR_NOT_FOUND_ID, new String[]{id}));
        userDiscountRepository.delete(userDiscount);
        return new CommonResponseDto(true, MessageConstant.DELETE_USER_DISCOUNT_SUCCESSFULLY);
    }
}
