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
public class NewSubscriber extends BaseEventEntity {

  @Serial private static final long serialVersionUID = 500959105883077643L;

  UUID subscribedUserId;
  String subscribedUsername;
}
