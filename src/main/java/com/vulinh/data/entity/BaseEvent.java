package com.vulinh.data.entity;

import module java.base;

import com.vulinh.data.base.AbstractTimestampAuditableEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@MappedSuperclass
@Getter
@Setter
@Accessors(chain = true)
public abstract class BaseEvent<I extends Serializable> extends AbstractTimestampAuditableEntity<I>
    implements WithTimestamp<Instant> {

  @Serial private static final long serialVersionUID = 4970586822583858108L;

  // Not recommended to have such a deep nested inheritance
  // Make sure to write out the duplication rule in CI/CD flows
  protected Instant timestamp;

  protected UUID eventId;

  protected abstract static class BaseEventBuilder<
          I extends Serializable, E extends BaseEvent<I>, B extends BaseEventBuilder<I, E, B>>
      implements AbstractTimestampAuditableEntity.AbstractTimestampAuditableEntityBuilder<I, E, B> {

    protected Instant timestamp;
    protected UUID eventId;

    public B timestamp(Instant timestamp) {
      this.timestamp = timestamp;
      return self();
    }

    public B eventId(UUID eventId) {
      this.eventId = eventId;
      return self();
    }

    protected E populateCommonFields(E event, B builder) {
      event.setTimestamp(timestamp);
      event.setEventId(eventId);

      return event;
    }
  }
}
