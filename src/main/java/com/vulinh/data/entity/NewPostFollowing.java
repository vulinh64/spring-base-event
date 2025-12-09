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
public class NewPostFollowing extends BaseEventEntity {

  @Serial private static final long serialVersionUID = 4772515304893828163L;

  UUID postId;
}
