package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.OrderCreateDto;
import com.example.techstore.domain.dto.response.OrderDto;
import com.example.techstore.domain.entity.DiscountCode;
import com.example.techstore.domain.entity.Order;
import com.example.techstore.domain.entity.Order.OrderBuilder;
import com.example.techstore.domain.entity.Status;
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
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order mapOrderCreateDtoToOrder(OrderCreateDto orderCreateDto) {
        if ( orderCreateDto == null ) {
            return null;
        }

        OrderBuilder order = Order.builder();

        order.note( orderCreateDto.getNote() );
        order.shippingFee( orderCreateDto.getShippingFee() );
        order.paymentStatus( orderCreateDto.getPaymentStatus() );

        return order.build();
    }

    @Override
    public OrderDto mapOrderToOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setShippingDiscountCodeId( orderShippingDiscountCodeId( order ) );
        orderDto.setMoneyDiscountCodeId( orderMoneyDiscountCodeId( order ) );
        orderDto.setStatusId( orderStatusId( order ) );
        orderDto.setStatusName( orderStatusName( order ) );
        orderDto.setCreatedDate( order.getCreatedDate() );
        orderDto.setLastModifiedDate( order.getLastModifiedDate() );
        orderDto.setCreatedBy( order.getCreatedBy() );
        orderDto.setLastModifiedBy( order.getLastModifiedBy() );
        orderDto.setId( order.getId() );
        orderDto.setCustomerName( order.getCustomerName() );
        orderDto.setPhone( order.getPhone() );
        orderDto.setAddress( order.getAddress() );
        orderDto.setNote( order.getNote() );
        orderDto.setShippingFee( order.getShippingFee() );
        orderDto.setOriginalPrice( order.getOriginalPrice() );
        orderDto.setTotalPrice( order.getTotalPrice() );
        orderDto.setPaymentStatus( order.getPaymentStatus() );

        return orderDto;
    }

    @Override
    public List<OrderDto> mapOrdersToOrderDtos(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderDto> list = new ArrayList<OrderDto>( orders.size() );
        for ( Order order : orders ) {
            list.add( mapOrderToOrderDto( order ) );
        }

        return list;
    }

    private String orderShippingDiscountCodeId(Order order) {
        if ( order == null ) {
            return null;
        }
        DiscountCode shippingDiscountCode = order.getShippingDiscountCode();
        if ( shippingDiscountCode == null ) {
            return null;
        }
        String id = shippingDiscountCode.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String orderMoneyDiscountCodeId(Order order) {
        if ( order == null ) {
            return null;
        }
        DiscountCode moneyDiscountCode = order.getMoneyDiscountCode();
        if ( moneyDiscountCode == null ) {
            return null;
        }
        String id = moneyDiscountCode.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer orderStatusId(Order order) {
        if ( order == null ) {
            return null;
        }
        Status status = order.getStatus();
        if ( status == null ) {
            return null;
        }
        Integer id = status.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String orderStatusName(Order order) {
        if ( order == null ) {
            return null;
        }
        Status status = order.getStatus();
        if ( status == null ) {
            return null;
        }
        String name = status.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
