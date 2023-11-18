package com.example.userservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDto {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDate createdAt;
    public OrderDto(String productId, Integer qty, Integer unitPrice, Integer totalPrice,
        LocalDate createdAt) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }
}
