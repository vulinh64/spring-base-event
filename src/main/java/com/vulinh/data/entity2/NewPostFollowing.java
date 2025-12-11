package com.vulinh.data.entity2;

import module java.base;

import com.vulinh.data.entity2.ids.NewPostFollowingId;
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
public class NewPostFollowing extends AbstractEventEntity<NewPostFollowingId> {

  @Serial private static final long serialVersionUID = 3802351465886434024L;

  @EmbeddedId NewPostFollowingId id;

  String title;
  String excerpt;
}
