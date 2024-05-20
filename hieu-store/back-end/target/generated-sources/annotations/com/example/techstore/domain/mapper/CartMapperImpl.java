package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.CartCreateDto;
import com.example.techstore.domain.dto.response.CartDto;
import com.example.techstore.domain.dto.response.ProductOptionDto;
import com.example.techstore.domain.entity.Cart;
import com.example.techstore.domain.entity.Cart.CartBuilder;
import com.example.techstore.domain.entity.Product;
import com.example.techstore.domain.entity.ProductOption;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T01:31:30+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public Cart mapCartCreateDtoToCart(CartCreateDto cartCreateDto) {
        if ( cartCreateDto == null ) {
            return null;
        }

        CartBuilder cart = Cart.builder();

        cart.quantity( cartCreateDto.getQuantity() );

        return cart.build();
    }

    @Override
    public CartDto mapCartToCartDto(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartDto cartDto = new CartDto();

        cartDto.setProductOptionDto( productOptionToProductOptionDto( cart.getProductOption() ) );
        cartDto.setCreatedDate( cart.getCreatedDate() );
        cartDto.setLastModifiedDate( cart.getLastModifiedDate() );
        cartDto.setCreatedBy( cart.getCreatedBy() );
        cartDto.setLastModifiedBy( cart.getLastModifiedBy() );
        cartDto.setId( cart.getId() );
        cartDto.setQuantity( cart.getQuantity() );

        return cartDto;
    }

    @Override
    public List<CartDto> mapCartsToCartDtos(List<Cart> carts) {
        if ( carts == null ) {
            return null;
        }

        List<CartDto> list = new ArrayList<CartDto>( carts.size() );
        for ( Cart cart : carts ) {
            list.add( mapCartToCartDto( cart ) );
        }

        return list;
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

    protected ProductOptionDto productOptionToProductOptionDto(ProductOption productOption) {
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
}
