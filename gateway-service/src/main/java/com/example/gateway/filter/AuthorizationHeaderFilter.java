package com.example.gateway.filter;

import io.jsonwebtoken.Jwts;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationHeaderFilter extends
    AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
  private final Environment env;

  public AuthorizationHeaderFilter(Environment env) {
    super(Config.class);
    this.env = env;
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      ServerHttpRequest request = exchange.getRequest();

      if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
        return onError(exchange, "no authorization header", HttpStatus.UNAUTHORIZED);
      }
      String token = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
      String jwt = token.replace("Bearer", "");

      if (!isJwtValid(jwt)) {
        return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
      }

      return chain.filter(exchange);
    };
  }

  private boolean isJwtValid(String jwt) {

    String subject = null;

    try {
      subject = Jwts.parser()
          .setSigningKey(Base64.getEncoder().encodeToString(env.getProperty("token.secret").getBytes()))
          .parseClaimsJws(jwt).getBody()
          .getSubject();

    } catch (Exception e) {
      return false;
    }

    if (subject == null || subject.isEmpty()) {
      return false;
    }
    return true;
  }

  private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
    ServerHttpResponse response = exchange.getResponse();

    response.setStatusCode(httpStatus);
    return response.setComplete();
  }

  public static class Config {

  }
}
