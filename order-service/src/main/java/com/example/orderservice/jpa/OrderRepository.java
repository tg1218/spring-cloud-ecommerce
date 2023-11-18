package com.example.orderservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    OrderEntity findOrderByOrderId(String orderId);

    List<OrderEntity> findOrderByUserId(String userId);
}
