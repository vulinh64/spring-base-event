package com.vulinh.data.entity2;

import module java.base;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class NewComment extends AbstractEventActionUserEntity {

  @Serial private static final long serialVersionUID = 5514959148822006491L;

  // Comment ID
  @Id UUID id;

  UUID postId;
  String title;
  String excerpt;

  // Comment content, not post content
  String content;
}
