package com.vulinh.data.entity;

import module java.base;

import com.vulinh.data.entity.ids.NewPostFollowingId;
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
public class NewPostFollowing extends BaseAssociatedEvent<NewPostFollowingId> {

  @Serial private static final long serialVersionUID = 894118846005326877L;

  @Id NewPostFollowingId id;
}
