package com.example.techstore.service;

import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.request.CartCreateDto;
import com.example.techstore.domain.dto.request.CartUpdateDto;
import com.example.techstore.domain.dto.response.CartDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;

public interface CartService {

    PaginationResponseDto<CartDto> getAll(String userId, PaginationFullRequestDto paginationFullRequestDto);

    int getNumberItem(String userId);

    CartDto create(String userId, CartCreateDto cartCreateDto);

    CartDto updateById(String userId, String id, CartUpdateDto cartUpdateDto);

    CommonResponseDto deleteById(String userId, String id);

}
