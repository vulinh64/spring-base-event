package com.vulinh.data.entity2;

import module java.base;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEventActionUserEntity extends AbstractEventEntity<UUID> {

  @Serial private static final long serialVersionUID = -282495665192319403L;

  // For implementing classes that didn't contain actionUserId field
  UUID actionUserId;
}
