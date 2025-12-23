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

  @Override
  public NewComment setStatus(EventStatus status) {
    super.setStatus(status);
    return this;
  }
}
