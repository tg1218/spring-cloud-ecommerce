package com.example.userservice.web;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Environment env;

    @GetMapping("/health_check")
    public String status() {
        return "Working User service : " + env.getProperty("token.expiration_time");
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        UserDto registeredUser = userService.createUser(user.toUserDto());

        return new ResponseEntity<>(new ResponseUser(registeredUser), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        List<UserDto> users = userService.getUserByAll();

        return new ResponseEntity<>(users.stream().map(ResponseUser::new).toList(), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {
        UserDto user = userService.getUserById(userId);

        return new ResponseEntity<>(new ResponseUser(user), HttpStatus.OK);
    }
}
