package com.example.techstore.service;

import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.response.UserRoomDto;
import com.example.techstore.domain.entity.UserRoomId;

public interface UserRoomService {
    UserRoomDto getById(UserRoomId userRoomId);

    PaginationResponseDto<UserRoomDto> getAll(String userId, PaginationFullRequestDto paginationFullRequestDto);

    UserRoomDto create(String roomId, String userId);

}
