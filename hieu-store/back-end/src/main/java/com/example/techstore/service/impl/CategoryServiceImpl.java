package com.example.techstore.service.impl;

import com.example.techstore.constant.ErrorMessage;
import com.example.techstore.constant.MessageConstant;
import com.example.techstore.domain.dto.request.CategoryRequestDto;
import com.example.techstore.domain.dto.response.CategoryDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.entity.Category;
import com.example.techstore.domain.mapper.CategoryMapper;
import com.example.techstore.exception.NotFoundException;
import com.example.techstore.repository.CategoryRepository;
import com.example.techstore.service.CategoryService;
import com.example.techstore.service.UserService;
import com.example.techstore.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UploadFileUtil uploadFileUtil;
    private final UserService userService;

    @Override
    public CategoryDto getById(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{id}));
        return categoryMapper.mapCategoryToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryMapper.mapCategoryToCategoryDto(categoryRepository.findAll());
    }

    @Override
    public CategoryDto create(CategoryRequestDto createDto) {
        Category category = categoryMapper.mapCategoryCreateDtoToCategory(createDto);
        category.setAvatar(uploadFileUtil.uploadImage(createDto.getAvatar()));
        return categoryMapper.mapCategoryToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto update(String id, CategoryRequestDto updateDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{id}));

        categoryMapper.updateCategory(category, updateDto);
        if (updateDto.getAvatar() != null && !updateDto.getAvatar().isEmpty()) {
            uploadFileUtil.destroyImageWithUrl(category.getAvatar());
            category.setAvatar(uploadFileUtil.uploadImage(updateDto.getAvatar()));
        }
        return categoryMapper.mapCategoryToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CommonResponseDto deleteById(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{id}));
        categoryRepository.delete(category);
        return new CommonResponseDto(true, MessageConstant.DELETE_CATEGORY_SUCCESSFULLY);
    }
}
