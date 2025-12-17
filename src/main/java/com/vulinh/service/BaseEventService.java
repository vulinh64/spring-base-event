package com.vulinh.service;

import module java.base;

import com.vulinh.data.base.AbstractTimestampAuditableEntity;
import com.vulinh.data.event.EventMessageWrapper;
import com.vulinh.data.exception.ValidationException;
import com.vulinh.data.repository.BaseEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.lang.NonNull;

@Slf4j
public abstract class BaseEventService<
    T, E extends AbstractTimestampAuditableEntity<I>, I extends Serializable> {

  public void processEvent(EventMessageWrapper<T> event) {
    ensureValidPayload(event);

    ensureValidData(event.data());

    log.info("Processing event ({}): {}", event.eventType(), event);

    var entityId = getEntityId(event);

    var repository = getRepository();

    if (repository.existsById(entityId)) {
      log.info("An entity with ID {} already exists. Skipping event processing...", entityId);

      return;
    }

    repository.save(toEntity(event));
  }

  protected abstract void ensureValidData(@NonNull T data);

  @NonNull
  protected abstract I getEntityId(@NonNull EventMessageWrapper<T> event);

  @NonNull
  protected abstract BaseEventRepository<E, I> getRepository();

  @NonNull
  protected abstract E toEntity(@NonNull EventMessageWrapper<T> event);

  private void ensureValidPayload(@NonNull EventMessageWrapper<T> event) {
    if (ObjectUtils.anyNull(event.eventId(), event.eventType(), event.timestamp(), event.data())) {
      throw new ValidationException("Event payload is missing required fields");
    }

    var actionUser = event.actionUser();

    Objects.requireNonNull(actionUser);

    if (ObjectUtils.anyNull(actionUser.id(), actionUser.username())) {
      throw new ValidationException("Action user is missing required fields");
    }
  }
}
