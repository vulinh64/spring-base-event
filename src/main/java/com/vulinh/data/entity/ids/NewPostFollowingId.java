package com.vulinh.data.entity.ids;

import module java.base;

import com.vulinh.data.Identifiable;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Builder;

// There can only be all-args canonical constructor
@SuppressWarnings("LombokBuilderInspection")
@Embeddable
@Builder
public record NewPostFollowingId(UUID postId, UUID actionUserId)
    implements Serializable, Identifiable<NewPostFollowingId> {

  @Override
  public NewPostFollowingId getId() {
    return this;
  }
}
