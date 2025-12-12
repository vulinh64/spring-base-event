package com.vulinh.service;

import module java.base;

import com.vulinh.data.entity.NewPost;
import com.vulinh.data.event.EventMessageWrapper;
import com.vulinh.data.event.payload.NewPostEvent;
import com.vulinh.data.mapper.EventMapper;
import com.vulinh.data.repository.NewPostRepository;
import com.vulinh.utils.Validators;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewPostEventService extends BaseEventService<NewPostEvent, NewPost, UUID> {

  final NewPostRepository newPostRepository;

  @Override
  protected void ensureValidData(@NonNull NewPostEvent data) {
    Validators.POST_VALIDATORS.validate(data);
  }

  @Override
  protected @NonNull UUID getEntityId(@NonNull EventMessageWrapper<NewPostEvent> event) {
    return event.data().postId();
  }

  @Override
  protected @NonNull NewPostRepository getRepository() {
    return newPostRepository;
  }

  @Override
  protected @NonNull Function<EventMessageWrapper<NewPostEvent>, NewPost> getEntityConverter() {
    return EventMapper.INSTANCE::toNewPostEntity;
  }
}
