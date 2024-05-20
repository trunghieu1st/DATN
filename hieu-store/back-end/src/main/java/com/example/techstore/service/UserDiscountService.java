package com.example.techstore.service;

import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.request.UserDiscountCreateDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.dto.response.UserDiscountDto;

public interface UserDiscountService {
    UserDiscountDto getById(String id);

    PaginationResponseDto<UserDiscountDto> getAll(Boolean status, PaginationFullRequestDto paginationFullRequestDto);

    PaginationResponseDto<UserDiscountDto> getAllByUserId(String userId, Boolean type, Boolean status, PaginationFullRequestDto paginationFullRequestDto);

    UserDiscountDto create(String userId, UserDiscountCreateDto createDto);

    UserDiscountDto update(String id);

    UserDiscountDto addDiscountCode(String userId, String code);

    CommonResponseDto deleteById(String id);
}
