/*
 * Copyright (c) 2024.
 * Hadjersi Mohamed
 */

package com.doctor.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

  private LocalDateTime timestamp;
  private String message;
  private String path;
  private String errorCode;
}
