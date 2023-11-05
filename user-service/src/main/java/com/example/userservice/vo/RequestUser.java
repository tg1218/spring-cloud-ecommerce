package com.example.userservice.vo;

import com.example.userservice.dto.UserDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestUser {
    @NotNull
    @Size(min = 2)
    private String email;

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    @Size(min = 8)
    private String pwd;

    public UserDto toUserDto(){
        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setName(name);
        userDto.setPwd(pwd);

        return userDto;
    }
}
