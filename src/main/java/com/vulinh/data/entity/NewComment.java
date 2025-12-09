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
public class NewComment extends BaseEventEntity {

  @Serial private static final long serialVersionUID = 7260837485797588775L;

  UUID postId;
  String title;
  String content;
}
