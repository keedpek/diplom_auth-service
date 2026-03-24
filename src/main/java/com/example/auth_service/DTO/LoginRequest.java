package com.example.auth_service.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginRequest {

  @NotBlank(message = "Email обязателен")
  @Email(message = "Некорректный формат email")
  @Size(max = 255, message = "Email не длиннее 255 символов")
  private String email;

  @NotBlank(message = "Пароль обязателен")
  private String password;
}
