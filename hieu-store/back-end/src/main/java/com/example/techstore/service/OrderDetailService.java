package com.example.techstore.service;

import com.example.techstore.domain.dto.request.OrderProductRequestDto;
import com.example.techstore.domain.dto.response.OrderDetailDto;
import com.example.techstore.domain.entity.Order;
import com.example.techstore.domain.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    OrderDetailDto getById(String id, String userId);

    List<OrderDetailDto> getAllByOrderId(String orderId, String userId);

    OrderDetail create(Order order, OrderProductRequestDto orderProductRequestDto);

}
