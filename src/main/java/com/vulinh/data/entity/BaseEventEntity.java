package com.vulinh.data.entity;

import module java.base;

import com.vulinh.data.UuidIdentifiable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEventEntity implements UuidIdentifiable, Serializable {

  @Serial private static final long serialVersionUID = -1241700010477084168L;

  @Id UUID id;

  Instant timestamp;

  @CreatedDate Instant createdDate;

  UUID actionUserId;
  String actionUsername;

  @Enumerated(EnumType.STRING)
  EventStatus status = EventStatus.RECEIVED;

  @LastModifiedDate Instant lastProcessedDate;

  int retryCount = 0;

  String failureReason;

  @Override
  public final boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    var id = getId();

    return id != null
        && o instanceof BaseEventEntity other
        && getEffectiveClass(this) == getEffectiveClass(o)
        && Objects.equals(id, other.getId());
  }

  @Override
  public final int hashCode() {
    var id = getId();

    return id == null ? getEffectiveClass(this).hashCode() : id.hashCode();
  }

  static Class<?> getEffectiveClass(Object object) {
    return object instanceof HibernateProxy hibernateProxy
        ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass()
        : object.getClass();
  }
}
