package com.example.techstore.service.impl;

import com.example.techstore.constant.*;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.pagination.PagingMeta;
import com.example.techstore.domain.dto.request.OrderCreateDto;
import com.example.techstore.domain.dto.request.OrderProductRequestDto;
import com.example.techstore.domain.dto.request.OrderUpdateDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.dto.response.OrderDto;
import com.example.techstore.domain.entity.*;
import com.example.techstore.domain.mapper.OrderMapper;
import com.example.techstore.exception.ForbiddenException;
import com.example.techstore.exception.InvalidException;
import com.example.techstore.exception.NotFoundException;
import com.example.techstore.repository.*;
import com.example.techstore.service.OrderDetailService;
import com.example.techstore.service.OrderService;
import com.example.techstore.service.UserDiscountService;
import com.example.techstore.service.UserService;
import com.example.techstore.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final DiscountCodeRepository discountCodeRepository;
    private final StatusRepository statusRepository;
    private final UserDiscountRepository userDiscountRepository;
    private final ProductOptionRepository productOptionRepository;
    private final UserService userService;
    private final OrderMapper orderMapper;
    private final OrderDetailService orderDetailService;
    private final UserDiscountService userDiscountService;

    @Override
    public OrderDto getById(String id, String userId) {
        User user = userService.getById(userId);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Order.ERR_NOT_FOUND_ID, new String[]{id}));
        if (!order.getCreatedBy().equals(userId) && !user.getRole().getName().equals(RoleConstant.ADMIN)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }
        return orderMapper.mapOrderToOrderDto(order);
    }

    @Override
    public PaginationResponseDto<OrderDto> getAllByUserId(String userId, PaginationFullRequestDto paginationFullRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationFullRequestDto, SortByDataConstant.ORDER);
        Page<Order> orderPage = orderRepository.getAllByUserId(userId, pageable);
        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.ORDER, orderPage);
        List<OrderDto> orderDtos = orderMapper.mapOrdersToOrderDtos(orderPage.getContent());
        return new PaginationResponseDto<>(meta, orderDtos);
    }

    @Override
    public PaginationResponseDto<OrderDto> getAll(PaginationFullRequestDto paginationFullRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationFullRequestDto, SortByDataConstant.ORDER);
        Page<Order> orderPage = orderRepository.getAll(paginationFullRequestDto.getKeyword(), pageable);
        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.ORDER, orderPage);
        List<OrderDto> orderDtos = orderMapper.mapOrdersToOrderDtos(orderPage.getContent());
        return new PaginationResponseDto<>(meta, orderDtos);
    }

    @Override
    public OrderDto create(String userId, OrderCreateDto orderCreateDto) {
        Address address = addressRepository.findById(orderCreateDto.getAddressId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Address.ERR_NOT_FOUND_ID, new String[]{orderCreateDto.getAddressId()}));
        if (!address.getCreatedBy().equals(userId)) {
            throw new InvalidException(ErrorMessage.Order.ERR_INVALID_ADDRESS);
        }
        DiscountCode shipDiscountCode = null;
        if (orderCreateDto.getShippingDiscountCodeId() != null) {
            shipDiscountCode = discountCodeRepository.findById(orderCreateDto.getShippingDiscountCodeId())
                    .orElseThrow(() -> new NotFoundException(ErrorMessage.DiscountCode.ERR_NOT_FOUND_ID, new String[]{orderCreateDto.getShippingDiscountCodeId()}));
            if (shipDiscountCode.getType()) {
                throw new InvalidException(ErrorMessage.Order.ERR_INVALID_SHIP_DISCOUNT_CODE);
            }
            UserDiscount userDiscount = userDiscountRepository.getByUserIdAndDiscountCodeId(userId, orderCreateDto.getShippingDiscountCodeId());
            if (userDiscount == null) {
                throw new NotFoundException(ErrorMessage.Order.ERR_USER_NOT_SHIP_DISCOUNT_CODE);
            }
            if (!userDiscount.getStatus()) {
                throw new InvalidException(ErrorMessage.Order.ERR_SHIP_DISCOUNT_CODE_ALREADY_USE);
            }
            if (shipDiscountCode.getStartDate().isAfter(LocalDateTime.now())
                    || shipDiscountCode.getExpirationDate().isBefore(LocalDateTime.now())) {
                throw new InvalidException(ErrorMessage.Order.ERR_SHIP_DISCOUNT_CODE_NOT_BE_USED);
            }
        }
        DiscountCode moneyDiscountCode = null;
        if (orderCreateDto.getMoneyDiscountCodeId() != null) {
            moneyDiscountCode = discountCodeRepository.findById(orderCreateDto.getMoneyDiscountCodeId())
                    .orElseThrow(() -> new NotFoundException(ErrorMessage.DiscountCode.ERR_NOT_FOUND_ID, new String[]{orderCreateDto.getMoneyDiscountCodeId()}));
            if (!moneyDiscountCode.getType()) {
                throw new InvalidException(ErrorMessage.Order.ERR_INVALID_MONEY_DISCOUNT_CODE);
            }
            UserDiscount userDiscount1 = userDiscountRepository.getByUserIdAndDiscountCodeId(userId, orderCreateDto.getShippingDiscountCodeId());
            if (userDiscount1 == null) {
                throw new NotFoundException(ErrorMessage.Order.ERR_USER_NOT_MONEY_DISCOUNT_CODE);
            }
            if (!userDiscount1.getStatus()) {
                throw new InvalidException(ErrorMessage.Order.ERR_MONEY_DISCOUNT_CODE_ALREADY_USE);
            }
            if (moneyDiscountCode.getStartDate().isAfter(LocalDateTime.now())
                    || moneyDiscountCode.getExpirationDate().isBefore(LocalDateTime.now())) {
                throw new InvalidException(ErrorMessage.Order.ERR_MONEY_DISCOUNT_CODE_NOT_BE_USED);
            }
        }
        long originalPrice = 0L;
        for (OrderProductRequestDto requestDto : orderCreateDto.getOrderProductRequestDtos()) {
            ProductOption productOption = productOptionRepository.findById(requestDto.getProductOptionId())
                    .orElseThrow(() -> new NotFoundException(ErrorMessage.ProductOption.ERR_NOT_FOUND_ID, new String[]{requestDto.getProductOptionId()}));
            if (!productOption.getStatus() || productOption.getQuantity() < requestDto.getQuantity()) {
                throw new InvalidException(ErrorMessage.Order.ERR_INVALID_PRODUCT_OPTION);
            }
            originalPrice += requestDto.getQuantity() * productOption.getPrice();
        }
        long totalPrice = originalPrice + orderCreateDto.getShippingFee();
        if (shipDiscountCode != null) {
            totalPrice -= (orderCreateDto.getShippingFee() >= shipDiscountCode.getDiscountAmount())
                    ? shipDiscountCode.getDiscountAmount() : orderCreateDto.getShippingFee();
        }
        if (moneyDiscountCode != null) {
            totalPrice -= (originalPrice >= moneyDiscountCode.getDiscountAmount())
                    ? moneyDiscountCode.getDiscountAmount() : originalPrice;
        }
        Order order = orderMapper.mapOrderCreateDtoToOrder(orderCreateDto);
        order.setCustomerName(address.getCustomerName());
        order.setPhone(address.getPhone());
        order.setAddress(address.getAddress());
        order.setShippingDiscountCode(shipDiscountCode);
        order.setMoneyDiscountCode(moneyDiscountCode);
        order.setOriginalPrice(originalPrice);
        order.setTotalPrice(totalPrice);
        order.setStatus(statusRepository.getById(StatusConstant.PENDING.getId()));
        orderRepository.save(order);
        if (shipDiscountCode != null) {
            userDiscountService.update(shipDiscountCode.getId());
        }
        if (moneyDiscountCode != null) {
            userDiscountService.update(moneyDiscountCode.getId());
        }
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderProductRequestDto requestDto : orderCreateDto.getOrderProductRequestDtos()) {
            orderDetails.add(orderDetailService.create(order, requestDto));
        }
        order.setOrderDetails(orderDetails);
        return orderMapper.mapOrderToOrderDto(order);
    }

    @Override
    public OrderDto updateById(String id, OrderUpdateDto orderUpdateDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Order.ERR_NOT_FOUND_ID, new String[]{id}));
        if (orderUpdateDto.getStatusId() > StatusConstant.CANCELLED.getId()
                || orderUpdateDto.getStatusId() <= StatusConstant.PENDING.getId()) {
            throw new InvalidException(ErrorMessage.OrderDetail.ERR_INVALID_STATUS_UPDATE);
        }
        if (order.getStatus().getId().equals(StatusConstant.DELIVERED.getId())
                || order.getStatus().getId().equals(StatusConstant.CANCELLED.getId())) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_UPDATE_DELETE);
        }
        order.setStatus(statusRepository.getById(orderUpdateDto.getStatusId()));
        if (orderUpdateDto.getStatusId().equals(StatusConstant.DELIVERED.getId())) {
            order.setPaymentStatus(true);
        }
        return orderMapper.mapOrderToOrderDto(orderRepository.save(order));
    }

    @Override
    public CommonResponseDto deleteById(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Order.ERR_NOT_FOUND_ID, new String[]{id}));
        orderRepository.delete(order);
        return new CommonResponseDto(true, MessageConstant.DELETE_ORDER_SUCCESSFULLY);
    }

}
