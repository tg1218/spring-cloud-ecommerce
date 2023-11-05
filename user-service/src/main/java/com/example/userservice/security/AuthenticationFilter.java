package com.example.userservice.security;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.RequestLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  UserService userService;
  Environment env;

  public AuthenticationFilter(
      AuthenticationManager authenticationManager, UserService userService, Environment env) {
    super(authenticationManager);
    this.userService = userService;
    this.env = env;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {

    try {
      RequestLogin credential = new ObjectMapper().readValue(request.getInputStream(),
          RequestLogin.class);

      return getAuthenticationManager().authenticate(
          new UsernamePasswordAuthenticationToken(
              credential.getEmail(),
              credential.getPassword(),
              new ArrayList<>()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {

    String email = ((User) authResult.getPrincipal()).getUsername();
    UserDto userDetail = userService.getUserDetailsByEmail(email);

    String token = Jwts.builder()
        .setSubject(userDetail.getUserId())
        .setExpiration(new Date(System.currentTimeMillis() + 3600000))
        .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(env.getProperty("token.secret").getBytes()))
        .compact();

    response.addHeader("token", token);
    response.addHeader("user-id", userDetail.getUserId());
  }
}
