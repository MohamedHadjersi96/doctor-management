/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException{
  private String resourceName;
  private String fieldName;
  private Long fieldValue;

  private String customMessage;

  public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue, String customMessage){
    super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
    this.customMessage = customMessage;
  }


}
