package com.example.orderservice.jpa;


import com.example.orderservice.dto.OrderDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "Orders")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id", nullable = false)
    private String productId;
    @Column(nullable = false)
    private Integer qty;
    @Column(nullable = false)
    private Integer unitPrice;
    @Column(nullable = false)
    private Integer totalPrice;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String orderId;
    @CreatedDate
    private LocalDate createdAt;

    public static OrderEntity of(OrderDto orderDto) {
        OrderEntity entity = new OrderEntity();

        entity.productId = orderDto.getProductId();
        entity.qty = orderDto.getQty();
        entity.unitPrice = orderDto.getUnitPrice();
        entity.totalPrice = orderDto.getTotalPrice();
        entity.orderId = orderDto.getOrderId();
        entity.userId = orderDto.getUserId();

        return entity;
    }

    public OrderDto toOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setProductId(productId);
        orderDto.setQty(qty);
        orderDto.setUnitPrice(unitPrice);
        orderDto.setTotalPrice(totalPrice);
        orderDto.setOrderId(orderId);
        orderDto.setCreatedAt(createdAt);

        return orderDto;
    }
}
