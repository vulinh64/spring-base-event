package com.vulinh.data.entity2;

import module java.base;

import com.vulinh.data.Identifiable;
import com.vulinh.data.entity.BaseEventEntity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEventEntity<I extends Serializable>
    implements Identifiable<I>, Serializable {

  @Serial private static final long serialVersionUID = -4940507457380841269L;

  String actionUsername;

  @CreatedDate Instant createdDate;

  @LastModifiedDate Instant updatedDate;

  @Override
  public final boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    var id = getId();

    return id != null
        && getEffectiveClass(this) == getEffectiveClass(o)
        && o instanceof BaseEventEntity other
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
