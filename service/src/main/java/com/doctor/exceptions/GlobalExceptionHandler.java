/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                      WebRequest webRequest){
    final ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            exception.getMessage(),
            webRequest.getDescription(false),
            exception.getCustomMessage()
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(SpecialityAlreadyExistException.class)
  public ResponseEntity<ErrorDetails> handleExistingSpecialityException(SpecialityAlreadyExistException exception,
                                                                      WebRequest webRequest){
    final ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            exception.getMessage(),
            webRequest.getDescription(false),
            "SPECIALITY_ALREADY_EXISTS"
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  // This specific method handles validation exceptions
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDetails> handleValidationException(MethodArgumentNotValidException exception,
                                                                WebRequest webRequest) {
    final String validationMessage = Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage();

    final ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            validationMessage,
            webRequest.getDescription(false),
            "VALIDATION_ERROR"
    );

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  // This is a global exception handler
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception,
                                                            WebRequest webRequest){
    final ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            exception.getMessage(),
            webRequest.getDescription(false),
            "INTERNAL SERVER ERROR"
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
