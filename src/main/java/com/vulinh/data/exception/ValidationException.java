package com.vulinh.data.exception;

import module java.base;

public class ValidationException extends RuntimeException {

  @Serial private static final long serialVersionUID = -2158815899819177660L;

  public ValidationException(String message) {
    super(message);
  }
}
