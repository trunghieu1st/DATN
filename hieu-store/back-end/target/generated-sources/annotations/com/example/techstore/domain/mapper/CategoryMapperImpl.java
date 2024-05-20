package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.CategoryRequestDto;
import com.example.techstore.domain.dto.response.CategoryDto;
import com.example.techstore.domain.entity.Category;
import com.example.techstore.domain.entity.Category.CategoryBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T01:31:31+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category mapCategoryCreateDtoToCategory(CategoryRequestDto createDto) {
        if ( createDto == null ) {
            return null;
        }

        CategoryBuilder category = Category.builder();

        category.name( createDto.getName() );
        category.description( createDto.getDescription() );

        return category.build();
    }

    @Override
    public CategoryDto mapCategoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setCreatedDate( category.getCreatedDate() );
        categoryDto.setLastModifiedDate( category.getLastModifiedDate() );
        categoryDto.setCreatedBy( category.getCreatedBy() );
        categoryDto.setLastModifiedBy( category.getLastModifiedBy() );
        categoryDto.setId( category.getId() );
        categoryDto.setName( category.getName() );
        categoryDto.setAvatar( category.getAvatar() );
        categoryDto.setDescription( category.getDescription() );

        return categoryDto;
    }

    @Override
    public List<CategoryDto> mapCategoryToCategoryDto(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>( categories.size() );
        for ( Category category : categories ) {
            list.add( mapCategoryToCategoryDto( category ) );
        }

        return list;
    }

    @Override
    public void updateCategory(Category category, CategoryRequestDto updateDto) {
        if ( updateDto == null ) {
            return;
        }

        if ( updateDto.getName() != null ) {
            category.setName( updateDto.getName() );
        }
        if ( updateDto.getDescription() != null ) {
            category.setDescription( updateDto.getDescription() );
        }
    }
}
