package com.vulinh.data.entity.ids;

import module java.base;

import com.vulinh.data.base.Identifiable;
import jakarta.persistence.Embeddable;
import lombok.Builder;

// There can only be all-args canonical constructor
@SuppressWarnings("LombokBuilderInspection")
@Embeddable
@Builder
public record NewSubscriberId(UUID subscribedUserId, UUID actionUserId)
    implements Serializable, Identifiable<NewSubscriberId> {

  @Override
  public NewSubscriberId getId() {
    return this;
  }
}
