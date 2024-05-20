package com.example.techstore.service;

import com.example.techstore.domain.dto.request.CategoryRequestDto;
import com.example.techstore.domain.dto.response.CategoryDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryDto getById(String id);

    List<CategoryDto> getAll();

    CategoryDto create(CategoryRequestDto createDto);

    CategoryDto update(String id, CategoryRequestDto updateDto);

    CommonResponseDto deleteById(String id);
}
