package com.example.userservice.service;


import com.example.userservice.dto.OrderDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.jpa.UserRepository;
import com.example.userservice.vo.ResponseOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final RestTemplate restTemplate;
  private final Environment env;

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

    String orderURL = String.format(env.getProperty("order-service.url"), user.getUserId());
    List<ResponseOrder> userOrders = restTemplate.exchange(orderURL, HttpMethod.GET,
        null, new ParameterizedTypeReference<List<ResponseOrder>>() {
        }).getBody();

    UserDto userDto = user.toUserDto();

    if(userOrders != null && (long) userOrders.size() > 0){
      userDto.setOrders(userOrders.stream().map(
          or -> new OrderDto(or.getProductId(), or.getQty(), or.getUnitPrice(), or.getTotalPrice(),
              or.getCreatedAt())).toList());
    }

    return userDto;
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
