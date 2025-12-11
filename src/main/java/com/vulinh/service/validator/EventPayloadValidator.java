package com.vulinh.service.validator;

import module java.base;

public interface EventPayloadValidator<T> {

  Predicate<T> predicate();

  String errorMessage();

  default boolean isValid(T payload) {
    return predicate().test(payload);
  }
}
