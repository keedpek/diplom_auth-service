package com.example.auth_service.DTO;

import lombok.Data;

@Data
public class LoginRequest {
  private String email;
  private String password;
}
