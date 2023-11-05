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
}
