package com.example.userservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findUserByUserId(String userId);

    UserEntity findUserByEmail(String email);
}
