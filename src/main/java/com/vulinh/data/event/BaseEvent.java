package com.vulinh.data.event;

import module java.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vulinh.data.UuidIdentifiable;

public interface BaseEvent extends UuidIdentifiable {

  UUID eventId();

  // eventId is enough
  @JsonIgnore
  @Override
  default UUID getId() {
    return eventId();
  }
}
