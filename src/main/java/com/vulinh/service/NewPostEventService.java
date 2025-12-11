package com.vulinh.service;

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
public class NewPostEventService extends BaseEventService<NewPostEvent> {

  final NewPostRepository newPostRepository;

  @Override
  protected void ensureValidData(@NonNull NewPostEvent data) {
    Validators.POST_VALIDATORS.validate(data);
  }

  @Override
  protected void processEventInternal(EventMessageWrapper<NewPostEvent> event) {
    newPostRepository.save(EventMapper.INSTANCE.toNewPostEntity(event));
  }
}
