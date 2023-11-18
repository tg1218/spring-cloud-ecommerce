package com.example.userservice.vo;

import com.example.userservice.dto.OrderDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDate createdAt;

    public static ResponseOrder of(OrderDto orderDto) {
        ResponseOrder order = new ResponseOrder();
        order.setProductId(orderDto.getProductId());
        order.setQty(orderDto.getQty());
        order.setUnitPrice(orderDto.getUnitPrice());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setCreatedAt(orderDto.getCreatedAt());
        return order;
    }
}
