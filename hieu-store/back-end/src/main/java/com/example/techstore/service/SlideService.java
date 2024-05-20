package com.example.techstore.service;

import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.request.SlideRequestDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.dto.response.SlideDto;

public interface SlideService {
    SlideDto getById(String id);

    PaginationResponseDto<SlideDto> getAll(PaginationFullRequestDto paginationFullRequestDto);

    PaginationResponseDto<SlideDto> getByStatus(PaginationFullRequestDto paginationFullRequestDto, Boolean status);

    SlideDto create(SlideRequestDto createDto);

    SlideDto update(String id, SlideRequestDto updateDto);

    CommonResponseDto deleteById(String id);
}
