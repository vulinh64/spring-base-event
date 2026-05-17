package com.vulinh.data.entity;

import module java.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vulinh.data.base.Identifiable;
import com.vulinh.data.entity.NewPostFollowing.NewPostFollowingId;
import com.vulinh.data.entity.NewPostFollowing.NewPostFollowingId.NewPostFollowingIdBuilder;
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
public class NewPostFollowing extends BaseAssociatedEvent<NewPostFollowingId> {

  @Serial private static final long serialVersionUID = 894118846005326877L;

  @Id NewPostFollowingId id;

  String title;
  String excerpt;

  public static NewPostFollowingBuilder builder() {
    return new NewPostFollowingBuilder();
  }

  public static class NewPostFollowingBuilder
      extends BaseAssociatedEventBuilder<
          NewPostFollowingId, NewPostFollowing, NewPostFollowingBuilder> {

    final NewPostFollowingIdBuilder idBuilder = NewPostFollowingId.builder();

    String title;
    String excerpt;

    public NewPostFollowingBuilder id(NewPostFollowingId id) {
      idBuilder.postId(id.postId()).actionUserId(id.actionUserId());
      return this;
    }

    public NewPostFollowingBuilder postId(UUID postId) {
      idBuilder.postId(postId);
      return this;
    }

    public NewPostFollowingBuilder actionUserId(UUID actionUserId) {
      idBuilder.actionUserId(actionUserId);
      return this;
    }

    public NewPostFollowingBuilder title(String title) {
      this.title = title;
      return self();
    }

    public NewPostFollowingBuilder excerpt(String excerpt) {
      this.excerpt = excerpt;
      return self();
    }

    @Override
    public NewPostFollowing build() {
      var object = new NewPostFollowing(idBuilder.build(), title, excerpt);

      populateCommonFields(object);

      return object;
    }

    @Override
    public NewPostFollowingBuilder self() {
      return this;
    }
  }

  // There can only be all-args canonical constructor
  @Embeddable
  @Builder
  public record NewPostFollowingId(UUID postId, UUID actionUserId)
      implements Serializable, Identifiable<NewPostFollowingId> {

    @JsonIgnore
    @Override
    public NewPostFollowingId getId() {
      return this;
    }
  }
}
