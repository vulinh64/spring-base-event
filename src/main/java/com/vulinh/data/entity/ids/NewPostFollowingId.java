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
public record NewPostFollowingId(UUID postId, UUID actionUserId)
    implements Serializable, Identifiable<NewPostFollowingId> {

  @JsonIgnore
  @Override
  public NewPostFollowingId getId() {
    return this;
  }
}
