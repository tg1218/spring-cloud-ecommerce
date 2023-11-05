package com.example.userservice.service;


import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.jpa.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public UserDto createUser(UserDto userDto) {
    userDto.setUserId(UUID.randomUUID().toString());

    userDto.setEncrytedPwd(passwordEncoder.encode(userDto.getPwd()));

    repository.save(userDto.toEntity());

    return userDto;
  }

  @Override
  public UserDto getUserById(String userId) {
    UserEntity user = repository.findUserByUserId(userId);

    if (user == null) {
      throw new UsernameNotFoundException("User Not Found");
    }

    return user.toUserDto();
  }

  @Override
  public List<UserDto> getUserByAll() {
    List<UserEntity> users = repository.findAll();

    return users.stream().map(UserEntity::toUserDto).toList();
  }

  @Override
  public UserDto getUserDetailsByEmail(String email) {
    UserEntity user = repository.findUserByEmail(email);

    if (user == null) {
      throw new UsernameNotFoundException("User Not Found");
    }

    return user.toUserDto();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity user = repository.findUserByEmail(username);

    if (user == null) {
      throw new UsernameNotFoundException(username);
    }

    return new User(user.getEmail(), user.getEncrytedPwd(),
        true, true, true, true,
        new ArrayList<>());
  }
}
