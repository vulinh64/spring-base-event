package com.vulinh.data.entity;

import module java.base;

import com.vulinh.data.entity.ids.NewPostFollowingId;
import com.vulinh.data.entity.ids.NewPostFollowingId.NewPostFollowingIdBuilder;
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
      return populateCommonFields(new NewPostFollowing(idBuilder.build(), title, excerpt), this);
    }

    @Override
    public NewPostFollowingBuilder self() {
      return this;
    }
  }
}
