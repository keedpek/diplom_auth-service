package com.example.auth_service.service.serviceImpl;

import com.example.auth_service.DTO.AuthResponse;
import com.example.auth_service.DTO.LoginRequest;
import com.example.auth_service.DTO.RegisterRequest;
import com.example.auth_service.entity.Role;
import com.example.auth_service.entity.User;
import com.example.auth_service.enums.RoleTypes;
import com.example.auth_service.exceptions.IncorrectPasswordException;
import com.example.auth_service.exceptions.PasswordConfirmException;
import com.example.auth_service.exceptions.UserAlreadyExistsException;
import com.example.auth_service.exceptions.NotFoundException;
import com.example.auth_service.repository.RoleRepository;
import com.example.auth_service.repository.UserRepository;
import com.example.auth_service.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public AuthResponse register(RegisterRequest registerRequest) {
    log.info("Регистрация пользователя: {}", registerRequest.getEmail());

    if (userRepository.existsByEmail(registerRequest.getEmail())) {
      log.warn("Регистрация с существующим email: {}", registerRequest.getEmail());
      throw new UserAlreadyExistsException("Пользователь с таким email уже зарегистрирован");
    }

    if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
      log.warn("Подтверждение пароля не совпадает с паролем, пользователь: {}", registerRequest.getEmail());
      throw new PasswordConfirmException("Пароли не совпадают");
    }

    User user = User.builder()
            .id(UUID.randomUUID())
            .email(registerRequest.getEmail())
            .hashedPassword(passwordEncoder.encode(registerRequest.getPassword()))
            .createdAt(LocalDateTime.now())
            .build();

    Role role = roleRepository
            .findByName(RoleTypes.EMPLOYEE)
            .orElseThrow(() -> new NotFoundException("Роль не найдена"));
    user.addRole(role);

    User savedUser = userRepository.save(user);
    log.info("Пользователь успешно зарегистрирован: id={}", savedUser.getId());

    return addTokensToUser(savedUser);
  }

  @Override
  public AuthResponse login(LoginRequest loginRequest) {
    log.info("Попытка входа: email={}", loginRequest.getEmail());

    User user = userRepository
            .findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> {
              log.warn("Неудачная попытка входа: email={} не зарегистрирован", loginRequest.getEmail());
              return new NotFoundException("Пользователь не найден");
            });

    if (!passwordEncoder.matches(loginRequest.getPassword(), user.getHashedPassword())) {
      log.warn("Неудачная попытка входа: пользователь с email={} ввел неверный пароль", loginRequest.getEmail());
      throw new IncorrectPasswordException("Неверный пароль");
    }

    log.info("Успешный вход: userId={}", user.getId());
    return addTokensToUser(user);
  }

  private AuthResponse addTokensToUser(User user) {
    return AuthResponse.builder()
            .accessToken("access_token")
            .refreshToken("refresh_token")
            .userID(user.getId().toString())
            .build();
  }
}
