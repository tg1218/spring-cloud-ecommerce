package com.example.orderservice.vo;

import com.example.orderservice.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private String orderId;
    private LocalDate createdAt;

    public ResponseOrder(OrderDto orderDto) {
        this.productId = orderDto.getProductId();
        this.qty = orderDto.getQty();
        this.unitPrice = orderDto.getUnitPrice();
        this.totalPrice = orderDto.getTotalPrice();
        this.orderId = orderDto.getOrderId();
        this.createdAt = orderDto.getCreatedAt();
    }
}
