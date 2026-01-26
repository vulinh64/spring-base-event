package com.vulinh.data.entity;

import module java.base;

import com.vulinh.data.EventStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@MappedSuperclass
@Getter
@Setter
@Accessors(chain = true)
public abstract class BaseNexusEvent extends BaseEvent<UUID> {

  @Serial private static final long serialVersionUID = 7131473974913315938L;

  @Id UUID id;

  UUID actionUserId;
  String actionUsername;

  @Enumerated(EnumType.STRING)
  EventStatus status = EventStatus.RECEIVED;

  int retryCount = 0;
  String failureReason;

  public abstract static class BaseNexusBuilder<
          E extends BaseNexusEvent, B extends BaseNexusBuilder<E, B>>
      extends BaseEventBuilder<UUID, E, B> {

    protected UUID id;
    protected UUID actionUserId;
    protected String actionUsername;
    protected EventStatus status = EventStatus.RECEIVED;
    protected int retryCount = 0;
    protected String failureReason;

    public B id(UUID id) {
      this.id = id;
      return self();
    }

    public B actionUserId(UUID actionUserId) {
      this.actionUserId = actionUserId;
      return self();
    }

    public B actionUsername(String actionUsername) {
      this.actionUsername = actionUsername;
      return self();
    }

    public B status(EventStatus status) {
      this.status = status;
      return self();
    }

    public B retryCount(int retryCount) {
      this.retryCount = retryCount;
      return self();
    }

    public B failureReason(String failureReason) {
      this.failureReason = failureReason;
      return self();
    }

    @Override
    protected E populateCommonFields(E nexusEvent, B builder) {
      nexusEvent.setActionUserId(builder.actionUserId);
      nexusEvent.setActionUsername(builder.actionUsername);
      nexusEvent.setStatus(builder.status);
      nexusEvent.setRetryCount(builder.retryCount);
      nexusEvent.setFailureReason(builder.failureReason);
      nexusEvent.setId(id);

      return super.populateCommonFields(nexusEvent, builder);
    }
  }
}
