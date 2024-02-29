/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.exceptions;

public class PatientAlreadyExistException extends RuntimeException {
  private String message;

  public PatientAlreadyExistException(String message){
    super(message);
    this.message = message;
  }
}
