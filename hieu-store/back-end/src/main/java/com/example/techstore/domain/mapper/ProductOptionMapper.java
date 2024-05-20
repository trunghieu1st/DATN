package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.ProductOptionCreateDto;
import com.example.techstore.domain.dto.request.ProductOptionUpdateDto;
import com.example.techstore.domain.dto.response.ProductOptionDto;
import com.example.techstore.domain.dto.response.ProductOptionSimpleDto;
import com.example.techstore.domain.entity.ProductOption;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductOptionMapper {
    @Mappings({
            @Mapping(target = "image", ignore = true)
    })
    ProductOption mapProductOptionCreateDtoToProductOption(ProductOptionCreateDto productOptionCreateDto);

    @Mappings({
            @Mapping(target = "productId", source = "product.id"),
            @Mapping(target = "productName", source = "product.name")
    })
    ProductOptionDto mapProductOptionToProductOptionDto(ProductOption productOption);

    ProductOptionSimpleDto mapProductOptionToProductOptionSimpleDto(ProductOption productOption);

    List<ProductOptionSimpleDto> mapProductsToProductOptionSimpleDtos(List<ProductOption> productOptions);

    List<ProductOptionDto> mapProductOptionsToProductOptionDtos(List<ProductOption> productOptions);

    @Mappings({
            @Mapping(target = "image", ignore = true)
    })
    void update(@MappingTarget ProductOption productOption, ProductOptionUpdateDto productOptionUpdateDto);
}
