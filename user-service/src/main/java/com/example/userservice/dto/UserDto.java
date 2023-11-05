package com.example.userservice.dto;

import com.example.userservice.jpa.UserEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private String encrytedPwd;

    private List<OrderDto> orders = new ArrayList<>();
    public UserEntity toEntity(){
        return UserEntity.builder()
                .email(email)
                .name(name)
                .userId(userId)
                .encrytedPwd(encrytedPwd)
                .build();
    }
}
