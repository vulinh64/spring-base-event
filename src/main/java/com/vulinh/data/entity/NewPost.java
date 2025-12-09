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
public class NewPost extends BaseEventEntity {

  @Serial private static final long serialVersionUID = -906294936710731043L;

  UUID postId;
  String title;
  String excerpt;
}
