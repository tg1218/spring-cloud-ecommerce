package com.example.userservice.jpa;

import com.example.userservice.dto.UserDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    @Id
    @GeneratedValue
    private Long Id;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false, unique = true)
    private String encrytedPwd;

    @CreatedDate
    private LocalDate createdAt;

    @Builder
    public UserEntity(String email, String name, String userId, String encrytedPwd) {
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.encrytedPwd = encrytedPwd;
    }

    public UserDto toUserDto(){
        UserDto userDto = new UserDto();

        userDto.setEmail(email);
        userDto.setName(name);
        userDto.setUserId(userId);

        return userDto;
    }
}
