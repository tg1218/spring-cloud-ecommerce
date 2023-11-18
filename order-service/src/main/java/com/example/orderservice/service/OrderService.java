package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDetails);

    OrderDto getOrderByOrderId(String orderId);

    List<OrderDto> getOrderByUserId(String userId);
}
