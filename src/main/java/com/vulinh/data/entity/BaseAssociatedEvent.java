package com.vulinh.data.entity;

import module java.base;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseAssociatedEvent<I extends Serializable> extends BaseEvent<I> {

  @Serial private static final long serialVersionUID = 371291557761709820L;

  String actionUsername;

  protected abstract static class BaseAssociatedEventBuilder<
          I extends Serializable,
          E extends BaseAssociatedEvent<I>,
          B extends BaseAssociatedEventBuilder<I, E, B>>
      extends BaseEventBuilder<I, E, B> {

    protected String actionUsername;

    public B actionUsername(String actionUsername) {
      this.actionUsername = actionUsername;
      return self();
    }

    @Override
    protected E populateCommonFields(E event, B builder) {
      event.setActionUsername(actionUsername);

      return super.populateCommonFields(event, builder);
    }
  }
}
