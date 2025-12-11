package com.vulinh.data.entity;

import module java.base;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseNexusEvent extends BaseAuditableEvent<UUID> {

  @Serial private static final long serialVersionUID = 7131473974913315938L;

  @Id UUID id;

  UUID actionUserId;
  String actionUsername;
}
