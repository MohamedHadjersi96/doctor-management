/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.exceptions;

public class DoctorAlreadyExistException extends RuntimeException {
  private String message;

  public DoctorAlreadyExistException(String message){
    super(message);
    this.message = message;
  }
}
