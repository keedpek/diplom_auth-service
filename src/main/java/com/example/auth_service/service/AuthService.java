package com.example.auth_service.service;

import com.example.auth_service.DTO.AuthResponse;
import com.example.auth_service.DTO.LoginRequest;
import com.example.auth_service.DTO.RegisterRequest;

public interface AuthService {
  AuthResponse register(RegisterRequest registerRequest);
  AuthResponse login(LoginRequest loginRequest);
}
