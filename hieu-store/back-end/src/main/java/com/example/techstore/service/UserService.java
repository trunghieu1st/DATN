package com.example.techstore.service;

import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.request.ChangePasswordRequestDto;
import com.example.techstore.domain.dto.request.NewPasswordRequestDto;
import com.example.techstore.domain.dto.request.UserCreateDto;
import com.example.techstore.domain.dto.request.UserUpdateDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.dto.response.UserDto;
import com.example.techstore.domain.entity.User;
import com.example.techstore.security.UserPrincipal;

public interface UserService {
    User getById(String userId);

    UserDto getUserDtoById(String userId);

    PaginationResponseDto<UserDto> getAll(PaginationFullRequestDto paginationFullRequestDto);

    UserDto getCurrentUser(UserPrincipal user);

    UserDto create(UserCreateDto userCreateDto);

    UserDto update(String id, UserUpdateDto userUpdateDto);

    CommonResponseDto changePassword(String id, ChangePasswordRequestDto changePasswordRequestDto);

    CommonResponseDto createNewPassword(String id, NewPasswordRequestDto newPasswordRequestDto);

    CommonResponseDto deleteById(String id);
}
