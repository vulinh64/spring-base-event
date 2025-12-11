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
@Builder
public class NewComment extends BaseNexusEvent {

  @Serial private static final long serialVersionUID = 7378850496418460328L;

  String content;

  UUID postId;
  String title;
  String excerpt;
}
