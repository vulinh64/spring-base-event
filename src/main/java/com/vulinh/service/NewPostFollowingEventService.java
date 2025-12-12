package com.vulinh.service;

import module java.base;

import com.vulinh.data.entity.NewPostFollowing;
import com.vulinh.data.entity.ids.NewPostFollowingId;
import com.vulinh.data.event.EventMessageWrapper;
import com.vulinh.data.event.payload.NewPostFollowingEvent;
import com.vulinh.data.mapper.EventMapper;
import com.vulinh.data.repository.NewPostFollowingRepository;
import com.vulinh.utils.Validators;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewPostFollowingEventService
    extends BaseEventService<NewPostFollowingEvent, NewPostFollowing, NewPostFollowingId> {

  final NewPostFollowingRepository newPostFollowingRepository;

  @Override
  protected void ensureValidData(@NonNull NewPostFollowingEvent data) {
    Validators.POST_VALIDATORS.validate(data);
  }

  @Override
  protected @NonNull NewPostFollowingId getEntityId(
          @NonNull EventMessageWrapper<NewPostFollowingEvent> event) {
    return NewPostFollowingId.builder()
        .postId(event.data().postId())
        .actionUserId(event.actionUser().id())
        .build();
  }

  @Override
  protected @NonNull NewPostFollowingRepository getRepository() {
    return newPostFollowingRepository;
  }

  @Override
  protected @NonNull Function<EventMessageWrapper<NewPostFollowingEvent>, NewPostFollowing>
      getEntityConverter() {
    return EventMapper.INSTANCE::toNewPostFollowingEntity;
  }
}
