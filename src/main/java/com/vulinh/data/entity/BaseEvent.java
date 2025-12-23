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
}
