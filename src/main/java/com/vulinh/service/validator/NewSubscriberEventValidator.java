package com.vulinh.service.validator;

import module java.base;

import com.vulinh.data.event.payload.NewSubscriberEvent;
import com.vulinh.utils.Validators;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Getter
@Accessors(fluent = true)
public enum NewSubscriberEventValidator implements EventPayloadValidator<NewSubscriberEvent> {
  VALID_SUBSCRIBED_ID(
      Validators.notNull(NewSubscriberEvent::subscribedUserId), "Subscribed ID must not be null"),
  VALID_SUBSCRIBED_USERNAME(
      Validators.notEmpty(NewSubscriberEvent::subscribedUsername),
      "Subscribed username must not be empty");

  final Predicate<NewSubscriberEvent> predicate;
  final String errorMessage;
}
