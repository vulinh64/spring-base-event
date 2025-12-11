package com.vulinh.data.entity;

import module java.base;

import com.vulinh.data.entity.ids.NewSubscriberId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NewSubscriber extends BaseAssociatedEvent<NewSubscriberId> {

  @Serial private static final long serialVersionUID = -9010837966738525256L;

  @Id NewSubscriberId id;

  String subscribedUsername;
}
