package com.example.techstore.controller;

import com.example.techstore.base.RestApiV1;
import com.example.techstore.base.VsResponseUtil;
import com.example.techstore.constant.UrlConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.request.CartCreateDto;
import com.example.techstore.domain.dto.request.CartUpdateDto;
import com.example.techstore.security.CurrentUser;
import com.example.techstore.security.UserPrincipal;
import com.example.techstore.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class CartController {

    private final CartService cartService;

    @Tag(name = "cart-controller")
    @Operation(summary = "API get all item of cart")
    @GetMapping(UrlConstant.Cart.GET_ALL)
    public ResponseEntity<?> getAllCart(@Parameter(name = "principal", hidden = true)
                                        @CurrentUser UserPrincipal user,
                                        @Valid @ParameterObject PaginationFullRequestDto paginationFullRequestDto) {
        return VsResponseUtil.success(cartService.getAll(user.getId(), paginationFullRequestDto));
    }

    @Tag(name = "cart-controller")
    @Operation(summary = "API get number item in cart")
    @GetMapping(UrlConstant.Cart.GET_NUMBER_OF_ITEM)
    public ResponseEntity<?> getNumberItem(@Parameter(name = "principal", hidden = true)
                                           @CurrentUser UserPrincipal user) {
        return VsResponseUtil.success(cartService.getNumberItem(user.getId()));
    }

    @Tag(name = "cart-controller")
    @Operation(summary = "API add item to cart")
    @PostMapping(UrlConstant.Cart.CREATE)
    public ResponseEntity<?> createCart(@Parameter(name = "principal", hidden = true)
                                        @CurrentUser UserPrincipal user,
                                        @Valid @RequestBody CartCreateDto cartCreateDto) {
        return VsResponseUtil.success(cartService.create(user.getId(), cartCreateDto));
    }

    @Tag(name = "cart-controller")
    @Operation(summary = "API update item in cart")
    @PatchMapping(UrlConstant.Cart.UPDATE)
    public ResponseEntity<?> updateCart(@PathVariable String id,
                                        @Parameter(name = "principal", hidden = true)
                                        @CurrentUser UserPrincipal user,
                                        @Valid @RequestBody CartUpdateDto cartUpdateDto) {
        return VsResponseUtil.success(cartService.updateById(user.getId(), id, cartUpdateDto));
    }

    @Tag(name = "cart-controller")
    @Operation(summary = "API delete item in cart")
    @DeleteMapping(UrlConstant.Cart.DELETE)
    public ResponseEntity<?> deleteCart(@PathVariable String id,
                                        @Parameter(name = "principal", hidden = true)
                                        @CurrentUser UserPrincipal user) {
        return VsResponseUtil.success(cartService.deleteById(user.getId(), id));
    }

}
