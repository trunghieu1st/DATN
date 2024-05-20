package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.OrderCreateDto;
import com.example.techstore.domain.dto.response.OrderDto;
import com.example.techstore.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    Order mapOrderCreateDtoToOrder(OrderCreateDto orderCreateDto);

    @Mappings({
            @Mapping(target = "shippingDiscountCodeId", source = "shippingDiscountCode.id"),
            @Mapping(target = "moneyDiscountCodeId", source = "moneyDiscountCode.id"),
            @Mapping(target = "statusId", source = "status.id"),
            @Mapping(target = "statusName", source = "status.name"),
    })
    OrderDto mapOrderToOrderDto(Order order);

    List<OrderDto> mapOrdersToOrderDtos(List<Order> orders);

}
