package com.example.techstore.controller;

import com.example.techstore.base.RestApiV1;
import com.example.techstore.base.VsResponseUtil;
import com.example.techstore.constant.UrlConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.security.CurrentUser;
import com.example.techstore.security.UserPrincipal;
import com.example.techstore.service.RoomService;
import com.example.techstore.service.UserRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class RoomController {
    private final RoomService roomService;
    private final UserRoomService userRoomService;

    @Tag(name = "room-controller")
    @Operation(summary = "API get room by id")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_SUPPORT')")
    @GetMapping(UrlConstant.Room.GET_BY_ID)
    public ResponseEntity<?> getRoomById(@PathVariable String id) {
        return VsResponseUtil.success(roomService.getById(id));
    }

    @Tag(name = "room-controller")
    @Operation(summary = "API get all room by Page")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_SUPPORT')")
    @GetMapping(UrlConstant.Room.GET_ALL)
    public ResponseEntity<?> getAllRoom(@Valid @ParameterObject PaginationFullRequestDto paginationFullRequestDto,
                                        @Parameter(name = "principal", hidden = true) @CurrentUser UserPrincipal user) {
        return VsResponseUtil.success(userRoomService.getAll(user.getId(), paginationFullRequestDto));
    }

}
