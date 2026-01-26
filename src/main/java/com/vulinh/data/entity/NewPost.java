package com.vulinh.data.entity;

import module java.base;

import com.vulinh.data.EventStatus;
import com.vulinh.data.entity.NewComment.NewCommentBuilder;
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
public class NewPost extends BaseNexusEvent {

  @Serial private static final long serialVersionUID = 7972890117944414414L;

  // Comment ID is located in the parent class (UUID id)
  // Ctrl + Click

  String title;
  String excerpt;

  public static NewCommentBuilder builder() {
    return new NewCommentBuilder();
  }

  @Override
  public NewPost setStatus(EventStatus status) {
    super.setStatus(status);
    return this;
  }

  public static class NewPostBuilder extends BaseNexusBuilder<NewPost, NewPostBuilder> {

    private String title;
    private String excerpt;

    public NewPostBuilder title(String title) {
      this.title = title;
      return this;
    }

    public NewPostBuilder excerpt(String excerpt) {
      this.excerpt = excerpt;
      return this;
    }

    @Override
    public NewPost build() {
      return populateCommonFields(new NewPost(title, excerpt), this);
    }

    @Override
    public NewPostBuilder self() {
      return this;
    }
  }
}
