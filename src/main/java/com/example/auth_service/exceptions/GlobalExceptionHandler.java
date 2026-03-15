package com.example.auth_service.exceptions;

import com.example.auth_service.DTO.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorResponseDto handleUserAlreadyExistsException(UserAlreadyExistsException ex, HttpServletRequest request) {
    return new ErrorResponseDto(
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT.getReasonPhrase(),
            ex.getMessage(),
            request.getRequestURI()
    );
  }

  @ExceptionHandler(IncorrectPasswordException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorResponseDto handleIncorrectPasswordException(IncorrectPasswordException ex, HttpServletRequest request) {
    return new ErrorResponseDto(
            HttpStatus.UNAUTHORIZED.value(),
            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
            ex.getMessage(),
            request.getRequestURI()
    );
  }

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponseDto handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
    return new ErrorResponseDto(
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            ex.getMessage(),
            request.getRequestURI()
    );
  }

  @ExceptionHandler(PasswordConfirmException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handlePasswordConfirmException(PasswordConfirmException ex, HttpServletRequest request) {
    return new ErrorResponseDto(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ex.getMessage(),
            request.getRequestURI()
    );
  }

}
