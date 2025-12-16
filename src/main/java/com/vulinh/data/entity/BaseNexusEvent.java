package com.vulinh.data.entity;

import module java.base;

import com.vulinh.data.EventStatus;
import com.vulinh.data.base.AbstractTimestampAuditableEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseNexusEvent extends AbstractTimestampAuditableEntity<UUID> {

  @Serial private static final long serialVersionUID = 7131473974913315938L;

  @Id UUID id;

  UUID actionUserId;
  String actionUsername;

  @Enumerated(EnumType.STRING)
  EventStatus status = EventStatus.RECEIVED;

  int retryCount = 0;
  String failureReason;
}
