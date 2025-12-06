package com.vulinh.data.event;

import module java.base;

import com.vulinh.data.UuidIdentifiable;
import lombok.Builder;
import lombok.With;

@Builder
@With
public record ActionUser(UUID id, String username) implements UuidIdentifiable {

  @Override
  public UUID getId() {
    return id;
  }
}
