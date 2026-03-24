package com.example.auth_service.controller;

import com.example.auth_service.DTO.AuthResponse;
import com.example.auth_service.DTO.LoginRequest;
import com.example.auth_service.DTO.RegisterRequest;
import com.example.auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
  private AuthService authService;

  @PostMapping("register")
  public AuthResponse register(
          @Valid @RequestBody RegisterRequest registerRequest
  ) {
    return authService.register(registerRequest);
  }

  @PostMapping("login")
  public AuthResponse login(
          @Valid @RequestBody LoginRequest loginRequest
  ) {
    return authService.login(loginRequest);
  }
}
