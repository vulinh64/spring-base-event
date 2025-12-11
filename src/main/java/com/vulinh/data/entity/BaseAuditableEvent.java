package com.vulinh.data.entity;

import module java.base;

import com.vulinh.data.Identifiable;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class BaseAuditableEvent<I extends Serializable>
    implements Identifiable<I>, Serializable {

  @Serial private static final long serialVersionUID = -3581289558183962649L;

  Instant timestamp;

  @CreatedDate Instant createdDateTime;

  @LastModifiedDate Instant updatedDateTime;

  @Override
  public final boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    var id = getId();

    return id != null
        && getEffectiveClass(this) == getEffectiveClass(o)
        && o instanceof BaseAuditableEvent<?> other
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
