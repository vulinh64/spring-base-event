package com.vulinh.data.entity;

import module java.base;

import com.vulinh.data.EventStatus;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class NewComment extends BaseNexusEvent {

  @Serial private static final long serialVersionUID = 7378850496418460328L;

  // Comment ID is located in the parent class (UUID id)
  // Ctrl + Click

  String content;
  UUID postId;
  String title;
  String excerpt;

  public static NewCommentBuilder builder() {
    return new NewCommentBuilder();
  }

  @Override
  public NewComment setStatus(EventStatus status) {
    super.setStatus(status);
    return this;
  }

  public static class NewCommentBuilder extends BaseNexusBuilder<NewComment, NewCommentBuilder> {

    String content;
    UUID postId;
    String title;
    String excerpt;

    public NewCommentBuilder content(String content) {
      this.content = content;
      return this;
    }

    public NewCommentBuilder postId(UUID postId) {
      this.postId = postId;
      return this;
    }

    public NewCommentBuilder title(String title) {
      this.title = title;
      return this;
    }

    public NewCommentBuilder excerpt(String excerpt) {
      this.excerpt = excerpt;
      return this;
    }

    @Override
    public NewComment build() {
      return populateCommonFields(new NewComment(content, postId, title, excerpt), this);
    }

    @Override
    public NewCommentBuilder self() {
      return this;
    }
  }
}
