package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.response.OrderDetailDto;
import com.example.techstore.domain.dto.response.ProductOptionDto;
import com.example.techstore.domain.entity.Order;
import com.example.techstore.domain.entity.OrderDetail;
import com.example.techstore.domain.entity.Product;
import com.example.techstore.domain.entity.ProductOption;
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
public class OrderDetailMapperImpl implements OrderDetailMapper {

    @Override
    public OrderDetailDto mapOrderDetailToOrderDetailDto(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }

        OrderDetailDto orderDetailDto = new OrderDetailDto();

        orderDetailDto.setProductOptionDto( productOptionToProductOptionDto( orderDetail.getProductOption() ) );
        orderDetailDto.setOrderId( orderDetailOrderId( orderDetail ) );
        orderDetailDto.setCreatedDate( orderDetail.getCreatedDate() );
        orderDetailDto.setLastModifiedDate( orderDetail.getLastModifiedDate() );
        orderDetailDto.setCreatedBy( orderDetail.getCreatedBy() );
        orderDetailDto.setLastModifiedBy( orderDetail.getLastModifiedBy() );
        orderDetailDto.setId( orderDetail.getId() );
        orderDetailDto.setQuantity( orderDetail.getQuantity() );
        orderDetailDto.setPrice( orderDetail.getPrice() );

        return orderDetailDto;
    }

    @Override
    public List<OrderDetailDto> mapOrderDetailsToOrderDetailDtos(List<OrderDetail> orderDetails) {
        if ( orderDetails == null ) {
            return null;
        }

        List<OrderDetailDto> list = new ArrayList<OrderDetailDto>( orderDetails.size() );
        for ( OrderDetail orderDetail : orderDetails ) {
            list.add( mapOrderDetailToOrderDetailDto( orderDetail ) );
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

    private String orderDetailOrderId(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }
        Order order = orderDetail.getOrder();
        if ( order == null ) {
            return null;
        }
        String id = order.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
