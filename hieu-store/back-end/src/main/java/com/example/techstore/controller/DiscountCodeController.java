package com.example.techstore.controller;

import com.example.techstore.base.RestApiV1;
import com.example.techstore.base.VsResponseUtil;
import com.example.techstore.constant.UrlConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.request.DiscountCodeRequestDto;
import com.example.techstore.service.DiscountCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class DiscountCodeController {
    private final DiscountCodeService discountCodeService;

    @Tag(name = "discountCode-controller")
    @Operation(summary = "API get discount code by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(UrlConstant.DiscountCode.GET_BY_ID)
    public ResponseEntity<?> getDiscountCodeById(@PathVariable String id) {
        return VsResponseUtil.success(discountCodeService.getById(id));
    }

    @Tag(name = "discountCode-controller")
    @Operation(summary = "API get all discount code by Page")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(UrlConstant.DiscountCode.GET_ALL)
    public ResponseEntity<?> getAllDiscountCode(@RequestParam(required = false) Boolean type,
                                                @Valid @ParameterObject PaginationFullRequestDto paginationFullRequestDto) {
        return VsResponseUtil.success(discountCodeService.getAll(type, paginationFullRequestDto));
    }

    @Tag(name = "discountCode-controller")
    @Operation(summary = "API create discount code")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(UrlConstant.DiscountCode.CREATE)
    public ResponseEntity<?> createDiscountCode(@Valid @RequestBody DiscountCodeRequestDto createDto) {
        return VsResponseUtil.success(discountCodeService.create(createDto));
    }

    @Tag(name = "discountCode-controller")
    @Operation(summary = "API update discount code")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(UrlConstant.DiscountCode.UPDATE)
    public ResponseEntity<?> updateDiscountCode(@PathVariable String id, @Valid @RequestBody DiscountCodeRequestDto updateDto) {
        return VsResponseUtil.success(discountCodeService.update(id, updateDto));
    }

    @Tag(name = "discountCode-controller")
    @Operation(summary = "API delete discount code")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(UrlConstant.DiscountCode.DELETE)
    public ResponseEntity<?> deleteDiscountCode(@PathVariable String id) {
        return VsResponseUtil.success(discountCodeService.deleteById(id));
    }
}
