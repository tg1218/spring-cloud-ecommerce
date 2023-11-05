package com.example.userservice.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestLogin {

  @NotNull
  @Size(min = 2)
  private String email;

  @NotNull
  @Size(min = 2)
  private String password;
}
