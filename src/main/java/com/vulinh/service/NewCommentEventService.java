package com.vulinh.service;

import com.vulinh.data.event.EventMessageWrapper;
import com.vulinh.data.event.payload.NewCommentEvent;
import com.vulinh.data.mapper.EventMapper;
import com.vulinh.data.repository.NewCommentRepository;
import com.vulinh.utils.Validators;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewCommentEventService extends BaseEventService<NewCommentEvent> {

  final NewCommentRepository newCommentRepository;

  @Override
  protected void ensureValidData(@NonNull NewCommentEvent data) {
    Validators.POST_VALIDATORS.validate(data);

    Validators.COMMENT_VALIDATORS.validate(data);
  }

  @Override
  protected void processEventInternal(EventMessageWrapper<NewCommentEvent> event) {
    newCommentRepository.save(EventMapper.INSTANCE.toNewCommentEntity(event));
  }
}
