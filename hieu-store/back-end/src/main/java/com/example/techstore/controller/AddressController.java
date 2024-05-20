package com.example.techstore.controller;

import com.example.techstore.base.RestApiV1;
import com.example.techstore.base.VsResponseUtil;
import com.example.techstore.constant.UrlConstant;
import com.example.techstore.domain.dto.request.AddressRequestDto;
import com.example.techstore.security.CurrentUser;
import com.example.techstore.security.UserPrincipal;
import com.example.techstore.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class AddressController {
    private final AddressService addressService;

    @Tag(name = "address-controller")
    @Operation(summary = "API get address by id")
    @GetMapping(UrlConstant.Address.GET_BY_ID)
    public ResponseEntity<?> getAddressById(@PathVariable String id,
                                            @Parameter(name = "principal", hidden = true)
                                            @CurrentUser UserPrincipal user) {
        return VsResponseUtil.success(addressService.getById(id, user.getId()));
    }

    @Tag(name = "address-controller")
    @Operation(summary = "API get all address current user")
    @GetMapping(UrlConstant.Address.GET_ALL)
    public ResponseEntity<?> getAllAddress(@Parameter(name = "principal", hidden = true)
                                           @CurrentUser UserPrincipal user) {
        return VsResponseUtil.success(addressService.getAllByCurrentUserId(user.getId()));
    }

    @Tag(name = "address-controller")
    @Operation(summary = "API get all address by user id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(UrlConstant.Address.GET_ALL_BY_USE_ID)
    public ResponseEntity<?> getAllAddressByUserId(@PathVariable String userId) {
        return VsResponseUtil.success(addressService.getAllByUserId(userId));
    }

    @Tag(name = "address-controller")
    @Operation(summary = "API get address default by user id")
    @GetMapping(UrlConstant.Address.GET_DEFAULT)
    public ResponseEntity<?> getAddressDefault(@Parameter(name = "principal", hidden = true)
                                               @CurrentUser UserPrincipal user) {
        return VsResponseUtil.success(addressService.getDefaultByCurrentUser(user.getId()));
    }

    @Tag(name = "address-controller")
    @Operation(summary = "API create address")
    @PostMapping(UrlConstant.Address.CREATE)
    public ResponseEntity<?> createAddress(@Valid @RequestBody AddressRequestDto addressRequestDto,
                                           @Parameter(name = "principal", hidden = true)
                                           @CurrentUser UserPrincipal user) {
        return VsResponseUtil.success(addressService.create(addressRequestDto, user.getId()));
    }

    @Tag(name = "address-controller")
    @Operation(summary = "API update address")
    @PatchMapping(UrlConstant.Address.UPDATE_BY_ID)
    public ResponseEntity<?> updateAddressById(@PathVariable String id,
                                               @Valid @RequestBody AddressRequestDto addressRequestDto,
                                               @Parameter(name = "principal", hidden = true)
                                               @CurrentUser UserPrincipal user) {
        return VsResponseUtil.success(addressService.updateById(id, addressRequestDto, user.getId()));
    }

    @Tag(name = "address-controller")
    @Operation(summary = "API change address default by id")
    @PatchMapping(UrlConstant.Address.CHANGE_DEFAULT_BY_ID)
    public ResponseEntity<?> changeAddressDefaultById(@PathVariable String id,
                                                      @Parameter(name = "principal", hidden = true)
                                                      @CurrentUser UserPrincipal user) {
        return VsResponseUtil.success(addressService.changeDefaultById(id, user.getId()));
    }

    @Tag(name = "address-controller")
    @Operation(summary = "API delete address by id")
    @DeleteMapping(UrlConstant.Address.DELETE_BY_ID)
    public ResponseEntity<?> deleteAddressById(@PathVariable String id,
                                               @Parameter(name = "principal", hidden = true)
                                               @CurrentUser UserPrincipal user) {
        return VsResponseUtil.success(addressService.deleteById(id, user.getId()));
    }

}
