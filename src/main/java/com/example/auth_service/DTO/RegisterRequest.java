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
public class RegisterRequest {

  @NotBlank(message = "Email обязателен")
  @Email(message = "Некорректный формат email")
  @Size(max = 255, message = "Email не длиннее 255 символов")
  private String email;

  @NotBlank(message = "Пароль обязателен")
  @Size(min = 8, max = 128, message = "Пароль должен содержать от 8 до 128 символов")
  private String password;

  @NotBlank(message = "Подтверждение пароля обязательно")
  @Size(min = 8, max = 128, message = "Пароль должен содержать от 8 до 128 символов")
  private String confirmPassword;

  @NotBlank(message = "Имя обязательно")
  @Size(max = 255, message = "Имя не длиннее 255 символов")
  private String name;
}
