package com.example.techstore.service;

import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.request.ProductOptionCreateDto;
import com.example.techstore.domain.dto.request.ProductOptionUpdateDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.dto.response.ProductOptionDto;
import com.example.techstore.domain.dto.response.ProductSimpleDto;

import java.util.List;

public interface ProductOptionService {
    ProductOptionDto getById(String id);

    List<ProductOptionDto> getAllByProductId(String productId);
    List<ProductOptionDto> getAll();
    ProductOptionDto create(ProductOptionCreateDto productOptionCreateDto);
    ProductOptionDto updateById(String id, ProductOptionUpdateDto productOptionUpdateDto);
    CommonResponseDto deleteById(String id);
}
