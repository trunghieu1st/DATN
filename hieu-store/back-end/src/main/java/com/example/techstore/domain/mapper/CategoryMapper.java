package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.CategoryRequestDto;
import com.example.techstore.domain.dto.response.CategoryDto;
import com.example.techstore.domain.entity.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {
    @Mappings({
            @Mapping(target = "avatar", ignore = true)
    })
    Category mapCategoryCreateDtoToCategory(CategoryRequestDto createDto);

    CategoryDto mapCategoryToCategoryDto(Category category);

    List<CategoryDto> mapCategoryToCategoryDto(List<Category> categories);

    @Mappings({
            @Mapping(target = "avatar", ignore = true)
    })
    void updateCategory(@MappingTarget Category category, CategoryRequestDto updateDto);
}
