package com.example.userservice.error;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    if (response.status() == 400) {
      if (methodKey.contains("getOrders")) {
        return new ResponseStatusException(HttpStatusCode.valueOf(response.status()),
            "User's order is empty.");
      }
    } else if (response.status() == 404) {
      return null;
    }
    else{
      return new Exception(response.reason());
    }
    return null;
  }
}
