package com.vulinh.data.entity2.ids;

import com.vulinh.data.Identifiable;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record NewSubscriberId(UUID actionUserId, UUID subscribedUserId)
    implements Serializable, Identifiable<NewSubscriberId> {

  @Override
  public NewSubscriberId getId() {
    return this;
  }
}
