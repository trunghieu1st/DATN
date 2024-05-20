package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.response.OrderDetailDto;
import com.example.techstore.domain.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderDetailMapper {

    @Mappings({
            @Mapping(target = "orderId", source = "order.id"),
            @Mapping(target = "productOptionDto", source = "productOption"),
            @Mapping(target = "productOptionDto.productId", source = "productOption.product.id"),
            @Mapping(target = "productOptionDto.productName", source = "productOption.product.name"),
    })
    OrderDetailDto mapOrderDetailToOrderDetailDto(OrderDetail orderDetail);

    List<OrderDetailDto> mapOrderDetailsToOrderDetailDtos(List<OrderDetail> orderDetails);

}
