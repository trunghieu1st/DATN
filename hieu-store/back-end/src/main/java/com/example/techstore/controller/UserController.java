package com.example.techstore.controller;

import com.example.techstore.base.RestApiV1;
import com.example.techstore.base.VsResponseUtil;
import com.example.techstore.constant.UrlConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.request.ChangePasswordRequestDto;
import com.example.techstore.domain.dto.request.NewPasswordRequestDto;
import com.example.techstore.domain.dto.request.UserCreateDto;
import com.example.techstore.domain.dto.request.UserUpdateDto;
import com.example.techstore.security.CurrentUser;
import com.example.techstore.security.UserPrincipal;
import com.example.techstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class UserController {

    private final UserService userService;

    @Tag(name = "user-controller")
    @Operation(summary = "API get user")
    @GetMapping(UrlConstant.User.GET_BY_ID)
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        return VsResponseUtil.success(userService.getUserDtoById(userId));
    }

    @Tag(name = "user-controller")
    @Operation(summary = "API get current user login")
    @GetMapping(UrlConstant.User.GET_CURRENT_USER)
    public ResponseEntity<?> getCurrentUser(@Parameter(name = "principal", hidden = true)
                                            @CurrentUser UserPrincipal user) {
        return VsResponseUtil.success(userService.getCurrentUser(user));
    }

    @Tag(name = "user-controller")
    @Operation(summary = "API get all user")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(UrlConstant.User.GET_ALL)
    public ResponseEntity<?> getAllUser(@Valid @ParameterObject PaginationFullRequestDto requestDTO) {
        return VsResponseUtil.success(userService.getAll(requestDTO));
    }

    @Tag(name = "user-controller")
    @Operation(summary = "API create user")
    @PostMapping(UrlConstant.User.CREATE)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return VsResponseUtil.success(userService.create(userCreateDto));
    }

    @Tag(name = "user-controller")
    @Operation(summary = "API update my profile")
    @PatchMapping(UrlConstant.User.UPDATE)
    public ResponseEntity<?> updateMyProfile(@Parameter(name = "principal", hidden = true)
                                             @CurrentUser UserPrincipal user,
                                             @Valid @ModelAttribute UserUpdateDto userUpdateDto) {
        return VsResponseUtil.success(userService.update(user.getId(), userUpdateDto));
    }

    @Tag(name = "user-controller")
    @Operation(summary = "API change password")
    @PatchMapping(UrlConstant.User.CHANGE_PASSWORD)
    public ResponseEntity<?> changePassword(@Parameter(name = "principal", hidden = true)
                                            @CurrentUser UserPrincipal user,
                                            @Valid @RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
        return VsResponseUtil.success(userService.changePassword(user.getId(), changePasswordRequestDto));
    }

    @Tag(name = "user-controller")
    //@Operation(summary = "API create new password")
    @PatchMapping(UrlConstant.User.CREATE_NEW_PASSWORD)
    public ResponseEntity<?> createNewPassword(@Parameter(name = "principal", hidden = true)
                                               @CurrentUser UserPrincipal user,
                                               @Valid @RequestBody NewPasswordRequestDto newPasswordRequestDto) {
        return VsResponseUtil.success(userService.createNewPassword(user.getId(), newPasswordRequestDto));
    }

    @Tag(name = "user-controller")
    @Operation(summary = "API delete user by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(UrlConstant.User.DELETE)
    public ResponseEntity<?> deleteUserById(@PathVariable String userId) {
        return VsResponseUtil.success(userService.deleteById(userId));
    }

}
