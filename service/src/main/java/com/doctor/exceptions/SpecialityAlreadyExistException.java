/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.exceptions;


public class SpecialityAlreadyExistException extends RuntimeException {
  private String message;

  public SpecialityAlreadyExistException(String message){
    super(message);
    this.message = message;
  }
}
