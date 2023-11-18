package com.example.orderservice.vo;

import com.example.orderservice.dto.OrderDto;
import lombok.Data;

@Data
public class RequestOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;

    public OrderDto toOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setProductId(productId);
        orderDto.setQty(qty);
        orderDto.setUnitPrice(unitPrice);

        return orderDto;
    }
}
