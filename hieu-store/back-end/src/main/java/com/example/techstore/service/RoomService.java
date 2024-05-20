package com.example.techstore.service;

import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.response.RoomDto;

public interface RoomService {
    RoomDto getById(String id);

    PaginationResponseDto<RoomDto> getAll(PaginationFullRequestDto paginationFullRequestDto);

    RoomDto create(String userId);

}