package com.vulinh.data.entity;

import module java.base;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NewComment extends BaseNexusEvent {

  @Serial private static final long serialVersionUID = 7378850496418460328L;

  // Comment ID is located in the parent class (UUID id)
  // Ctrl + Click

  String content;
  UUID postId;
  String title;
  String excerpt;
}
