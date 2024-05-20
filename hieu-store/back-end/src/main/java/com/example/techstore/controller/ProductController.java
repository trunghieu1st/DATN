package com.example.techstore.controller;

import com.example.techstore.base.RestApiV1;
import com.example.techstore.base.VsResponseUtil;
import com.example.techstore.constant.UrlConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.request.ProductRequestDto;
import com.example.techstore.service.ProductService;
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
public class ProductController {
    private final ProductService productService;

    @Tag(name = "product-controller")
    @Operation(summary = "API get product by id")
    @GetMapping(UrlConstant.Product.GET_BY_ID)
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        return VsResponseUtil.success(productService.getById(id));
    }

    @Tag(name = "product-controller")
    @Operation(summary = "API get all product")
    @GetMapping(UrlConstant.Product.GET_ALL)
    public ResponseEntity<?> getAllProduct(@RequestParam(name = "categoryId", required = false, defaultValue = "") String categoryId,
                                           @Valid @ParameterObject PaginationFullRequestDto paginationFullRequestDto) {
        return VsResponseUtil.success(productService.getAll(categoryId, paginationFullRequestDto));
    }

    @Tag(name = "product-controller")
    @Operation(summary = "API search product")
    @GetMapping(UrlConstant.Product.SEARCH)
    public ResponseEntity<?> searchProduct(@Valid @ParameterObject PaginationFullRequestDto paginationFullRequestDto) {
        return VsResponseUtil.success(productService.search(paginationFullRequestDto));
    }

    @Tag(name = "product-controller")
    @Operation(summary = "API create product")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(UrlConstant.Product.CREATE)
    public ResponseEntity<?> createProduct(@Valid @ModelAttribute ProductRequestDto productRequestDto) {
        return VsResponseUtil.success(productService.create(productRequestDto));
    }

    @Tag(name = "product-controller")
    @Operation(summary = "API update product by id")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(UrlConstant.Product.UPDATE)
    public ResponseEntity<?> updateProductById(@PathVariable String id,
                                               @Valid @ModelAttribute ProductRequestDto productRequestDto) {
        return VsResponseUtil.success(productService.updateById(id, productRequestDto));
    }

    @Tag(name = "product-controller")
    @Operation(summary = "API delete product by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(UrlConstant.Product.DELETE)
    public ResponseEntity<?> deleteProductById(@PathVariable String id) {
        return VsResponseUtil.success(productService.deleteById(id));
    }

}
