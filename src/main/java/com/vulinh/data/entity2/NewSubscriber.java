package com.vulinh.data.entity2;

import module java.base;

import com.vulinh.data.entity2.ids.NewSubscriberId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class NewSubscriber extends AbstractEventEntity<NewSubscriberId> {

  @Serial private static final long serialVersionUID = 503672439788290750L;

  @EmbeddedId NewSubscriberId id;

  String subscribedUsername;
}
