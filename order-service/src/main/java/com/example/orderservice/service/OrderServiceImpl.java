package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.jpa.OrderEntity;
import com.example.orderservice.jpa.OrderRepository;
import com.example.orderservice.messageQueue.OrderProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository repository;
    private final OrderProducerService orderProducerService;

    @Override
    public OrderDto createOrder(OrderDto orderDetails) {
        orderDetails.setOrderId(UUID.randomUUID().toString());
        orderDetails.setTotalPrice(orderDetails.getQty() * orderDetails.getUnitPrice());

        orderProducerService.send("orders", orderDetails);

        return orderDetails;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        OrderEntity orderEntity = repository.findOrderByOrderId(orderId);

        return orderEntity.toOrderDto();
    }

    @Override
    public List<OrderDto> getOrderByUserId(String userId) {
        List<OrderEntity> orderEntity = repository.findOrderByUserId(userId);

        return orderEntity.stream().map(OrderEntity::toOrderDto).toList();
    }
}
