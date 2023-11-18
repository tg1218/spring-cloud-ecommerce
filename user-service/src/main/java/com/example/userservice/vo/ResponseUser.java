package com.example.userservice.vo;

import com.example.userservice.dto.UserDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseUser {
    private String email;
    private String userId;
    private String name;

    private List<ResponseOrder> orders = new ArrayList<>();

    public ResponseUser (UserDto userDto) {
        this.email = userDto.getEmail();
        this.userId = userDto.getUserId();
        this.name = userDto.getName();
        this.orders = userDto.getOrders().stream().map(ResponseOrder::of).toList();
    }
}
