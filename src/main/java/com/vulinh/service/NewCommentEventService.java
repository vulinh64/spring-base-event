package com.vulinh.service;

import module java.base;

import com.vulinh.data.entity.NewComment;
import com.vulinh.data.event.EventMessageWrapper;
import com.vulinh.data.event.payload.NewCommentEvent;
import com.vulinh.data.mapper.EventMapper;
import com.vulinh.data.repository.NewCommentRepository;
import com.vulinh.utils.Validators;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewCommentEventService extends BaseEventService<NewCommentEvent, NewComment, UUID> {

  final NewCommentRepository newCommentRepository;

  @Override
  protected void ensureValidData(@NonNull NewCommentEvent data) {
    Validators.POST_VALIDATORS.validate(data);

    Validators.COMMENT_VALIDATORS.validate(data);
  }

  @Override
  protected @org.springframework.lang.NonNull UUID getEntityId(
      @NonNull EventMessageWrapper<NewCommentEvent> event) {
    return event.data().commentId();
  }

  @Override
  protected @NonNull NewCommentRepository getRepository() {
    return newCommentRepository;
  }

  @Override
  protected @NonNull NewComment toEntity(@NonNull EventMessageWrapper<NewCommentEvent> event) {
    return EventMapper.INSTANCE.toNewCommentEntity(event);
  }
}
