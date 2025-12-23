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
}
