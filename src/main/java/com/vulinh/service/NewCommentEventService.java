package com.vulinh.service;

import com.vulinh.data.event.EventMessageWrapper;
import com.vulinh.data.event.NewCommentEvent;
import com.vulinh.data.mapper.EventMapper;
import com.vulinh.data.repository.NewCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewCommentEventService extends BaseEventService<NewCommentEvent> {

  final NewCommentRepository newCommentRepository;

  @Override
  protected void processEventInternal(EventMessageWrapper<NewCommentEvent> event) {
    newCommentRepository.save(EventMapper.INSTANCE.toNewCommentEntity(event));
  }
}
