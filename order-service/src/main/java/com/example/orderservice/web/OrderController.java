package com.example.orderservice.web;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.vo.RequestOrder;
import com.example.orderservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order-service")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId, @RequestBody RequestOrder order) {
        OrderDto orderDto = order.toOrderDto();
        orderDto.setUserId(userId);

        orderService.createOrder(orderDto);

        return new ResponseEntity<>(new ResponseOrder(orderDto), HttpStatus.OK);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId) {
        List<OrderDto> orders = orderService.getOrderByUserId(userId);

        return new ResponseEntity<>(orders.stream().map(ResponseOrder::new).toList(), HttpStatus.OK);
    }
}
