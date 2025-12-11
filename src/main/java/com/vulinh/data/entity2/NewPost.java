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
public class NewPost extends AbstractEventActionUserEntity {

  @Serial private static final long serialVersionUID = -6624898930517798177L;

  // Post ID
  @Id UUID id;

  String title;
  String excerpt;
}
