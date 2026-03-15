package com.example.auth_service.exceptions;

public class PasswordConfirmException extends RuntimeException {
  public PasswordConfirmException(String message) {
    super(message);
  }
}
