package com.vulinh.data.entity2.ids;

import com.vulinh.data.Identifiable;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record NewPostFollowingId(UUID actionUserId, UUID postId)
    implements Serializable, Identifiable<NewPostFollowingId> {

  @Override
  public NewPostFollowingId getId() {
    return this;
  }
}
