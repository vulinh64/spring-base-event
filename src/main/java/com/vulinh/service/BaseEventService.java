package com.vulinh.service;

import com.vulinh.data.event.EventMessageWrapper;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.lang.NonNull;

@Slf4j
public abstract class BaseEventService<T> {

  public void processEvent(EventMessageWrapper<T> event) {
    ensureValidPayload(event);

    ensureValidData(event.data());

    log.info("Processing event ({}): {}", event.eventType(), event);

    processEventInternal(event);
  }

  protected void ensureValidData(@NonNull T data) {
    // NOOP
  }

  protected abstract void processEventInternal(EventMessageWrapper<T> event);

  private void ensureValidPayload(EventMessageWrapper<T> event) {
    var actionUser = event.actionUser();

    if (ObjectUtils.allNotNull(
        event.eventId(), event.eventType(), event.timestamp(), event.data())) {
      throw new IllegalArgumentException("Event payload is missing required fields");
    }

    Objects.requireNonNull(actionUser);

    if (ObjectUtils.allNotNull(actionUser.id(), actionUser.username())) {
      throw new IllegalArgumentException("Action user is missing required fields");
    }
  }
}
