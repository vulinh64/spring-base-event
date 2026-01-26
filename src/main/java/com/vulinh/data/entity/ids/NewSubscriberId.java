package com.vulinh.data.entity.ids;

import module java.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vulinh.data.base.Identifiable;
import jakarta.persistence.Embeddable;
import lombok.Builder;

// There can only be all-args canonical constructor
@SuppressWarnings("LombokBuilderInspection")
@Embeddable
@Builder
public record NewSubscriberId(UUID subscribedUserId, UUID actionUserId)
    implements Serializable, Identifiable<NewSubscriberId> {

  @JsonIgnore
  @Override
  public NewSubscriberId getId() {
    return this;
  }
}
