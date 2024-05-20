package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.ProductOptionCreateDto;
import com.example.techstore.domain.dto.request.ProductOptionUpdateDto;
import com.example.techstore.domain.dto.response.ProductOptionDto;
import com.example.techstore.domain.dto.response.ProductOptionSimpleDto;
import com.example.techstore.domain.entity.Product;
import com.example.techstore.domain.entity.ProductOption;
import com.example.techstore.domain.entity.ProductOption.ProductOptionBuilder;
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
public class ProductOptionMapperImpl implements ProductOptionMapper {

    @Override
    public ProductOption mapProductOptionCreateDtoToProductOption(ProductOptionCreateDto productOptionCreateDto) {
        if ( productOptionCreateDto == null ) {
            return null;
        }

        ProductOptionBuilder productOption = ProductOption.builder();

        productOption.ram( productOptionCreateDto.getRam() );
        productOption.storageCapacity( productOptionCreateDto.getStorageCapacity() );
        productOption.color( productOptionCreateDto.getColor() );
        productOption.price( productOptionCreateDto.getPrice() );
        productOption.quantity( productOptionCreateDto.getQuantity() );
        productOption.status( productOptionCreateDto.getStatus() );

        return productOption.build();
    }

    @Override
    public ProductOptionDto mapProductOptionToProductOptionDto(ProductOption productOption) {
        if ( productOption == null ) {
            return null;
        }

        ProductOptionDto productOptionDto = new ProductOptionDto();

        productOptionDto.setProductId( productOptionProductId( productOption ) );
        productOptionDto.setProductName( productOptionProductName( productOption ) );
        productOptionDto.setCreatedDate( productOption.getCreatedDate() );
        productOptionDto.setLastModifiedDate( productOption.getLastModifiedDate() );
        productOptionDto.setCreatedBy( productOption.getCreatedBy() );
        productOptionDto.setLastModifiedBy( productOption.getLastModifiedBy() );
        productOptionDto.setId( productOption.getId() );
        productOptionDto.setRam( productOption.getRam() );
        productOptionDto.setStorageCapacity( productOption.getStorageCapacity() );
        productOptionDto.setColor( productOption.getColor() );
        productOptionDto.setImage( productOption.getImage() );
        productOptionDto.setPrice( productOption.getPrice() );
        productOptionDto.setQuantity( productOption.getQuantity() );
        productOptionDto.setStatus( productOption.getStatus() );

        return productOptionDto;
    }

    @Override
    public ProductOptionSimpleDto mapProductOptionToProductOptionSimpleDto(ProductOption productOption) {
        if ( productOption == null ) {
            return null;
        }

        ProductOptionSimpleDto productOptionSimpleDto = new ProductOptionSimpleDto();

        productOptionSimpleDto.setId( productOption.getId() );
        productOptionSimpleDto.setRam( productOption.getRam() );
        productOptionSimpleDto.setStorageCapacity( productOption.getStorageCapacity() );
        productOptionSimpleDto.setColor( productOption.getColor() );
        productOptionSimpleDto.setImage( productOption.getImage() );
        productOptionSimpleDto.setPrice( productOption.getPrice() );
        productOptionSimpleDto.setQuantity( productOption.getQuantity() );
        productOptionSimpleDto.setStatus( productOption.getStatus() );

        return productOptionSimpleDto;
    }

    @Override
    public List<ProductOptionSimpleDto> mapProductsToProductOptionSimpleDtos(List<ProductOption> productOptions) {
        if ( productOptions == null ) {
            return null;
        }

        List<ProductOptionSimpleDto> list = new ArrayList<ProductOptionSimpleDto>( productOptions.size() );
        for ( ProductOption productOption : productOptions ) {
            list.add( mapProductOptionToProductOptionSimpleDto( productOption ) );
        }

        return list;
    }

    @Override
    public List<ProductOptionDto> mapProductOptionsToProductOptionDtos(List<ProductOption> productOptions) {
        if ( productOptions == null ) {
            return null;
        }

        List<ProductOptionDto> list = new ArrayList<ProductOptionDto>( productOptions.size() );
        for ( ProductOption productOption : productOptions ) {
            list.add( mapProductOptionToProductOptionDto( productOption ) );
        }

        return list;
    }

    @Override
    public void update(ProductOption productOption, ProductOptionUpdateDto productOptionUpdateDto) {
        if ( productOptionUpdateDto == null ) {
            return;
        }

        if ( productOptionUpdateDto.getRam() != null ) {
            productOption.setRam( productOptionUpdateDto.getRam() );
        }
        if ( productOptionUpdateDto.getStorageCapacity() != null ) {
            productOption.setStorageCapacity( productOptionUpdateDto.getStorageCapacity() );
        }
        if ( productOptionUpdateDto.getColor() != null ) {
            productOption.setColor( productOptionUpdateDto.getColor() );
        }
        if ( productOptionUpdateDto.getPrice() != null ) {
            productOption.setPrice( productOptionUpdateDto.getPrice() );
        }
        if ( productOptionUpdateDto.getQuantity() != null ) {
            productOption.setQuantity( productOptionUpdateDto.getQuantity() );
        }
        if ( productOptionUpdateDto.getStatus() != null ) {
            productOption.setStatus( productOptionUpdateDto.getStatus() );
        }
    }

    private String productOptionProductId(ProductOption productOption) {
        if ( productOption == null ) {
            return null;
        }
        Product product = productOption.getProduct();
        if ( product == null ) {
            return null;
        }
        String id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String productOptionProductName(ProductOption productOption) {
        if ( productOption == null ) {
            return null;
        }
        Product product = productOption.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
