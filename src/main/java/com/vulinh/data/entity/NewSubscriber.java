package com.vulinh.data.entity;

import module java.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vulinh.data.base.Identifiable;
import com.vulinh.data.entity.NewSubscriber.NewSubscriberId;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
@SuppressWarnings("java:S2160")
public class NewSubscriber extends BaseAssociatedEvent<NewSubscriberId> {

  @Serial private static final long serialVersionUID = -9010837966738525256L;

  @Id NewSubscriberId id;

  String subscribedUsername;

  public static NewSubscriberBuilder builder() {
    return new NewSubscriberBuilder();
  }

  public static class NewSubscriberBuilder
      extends BaseAssociatedEvent.BaseAssociatedEventBuilder<
          NewSubscriberId, NewSubscriber, NewSubscriberBuilder> {

    final NewSubscriberId.NewSubscriberIdBuilder idBuilder = NewSubscriberId.builder();

    String subscribedUsername;

    public NewSubscriberBuilder id(NewSubscriberId id) {
      idBuilder.subscribedUserId(id.subscribedUserId()).actionUserId(id.actionUserId());
      return this;
    }

    public NewSubscriberBuilder subscribedUserId(UUID subscribedUserId) {
      idBuilder.subscribedUserId(subscribedUserId);
      return this;
    }

    public NewSubscriberBuilder actionUserId(UUID actionUserId) {
      idBuilder.actionUserId(actionUserId);
      return this;
    }

    public NewSubscriberBuilder subscribedUsername(String subscribedUsername) {
      this.subscribedUsername = subscribedUsername;
      return this;
    }

    @Override
    public NewSubscriberBuilder self() {
      return this;
    }

    @Override
    public NewSubscriber build() {
      var object = new NewSubscriber(idBuilder.build(), subscribedUsername);

      populateCommonFields(object);

      return object;
    }
  }

  @Embeddable
  @Builder
  public record NewSubscriberId(UUID subscribedUserId, UUID actionUserId)
      implements Serializable, Identifiable<NewSubscriberId> {

    @JsonIgnore
    @Override
    public NewSubscriberId getId() {
      return this;
    }
  }
}
