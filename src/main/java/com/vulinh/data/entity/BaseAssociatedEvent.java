package com.vulinh.data.entity;

import module java.base;

import com.vulinh.data.EventStatus;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseAssociatedEvent<I extends Serializable> extends BaseAuditableEvent<I> {

  @Serial private static final long serialVersionUID = 371291557761709820L;

  String actionUsername;
  EventStatus status = EventStatus.RECEIVED;
  int retryCount = 0;
  String failureReason;
}
