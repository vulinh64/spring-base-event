package com.vulinh.data.entity;

import module java.base;

import com.vulinh.data.base.AbstractIdentifiable;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class BaseAuditableEvent<I extends Serializable> extends AbstractIdentifiable<I> {

  @Serial private static final long serialVersionUID = -3581289558183962649L;

  Instant timestamp;

  @CreatedDate Instant createdDateTime;

  @LastModifiedDate Instant updatedDateTime;

  // For future reference/tracking purposes
  UUID eventId;
}
