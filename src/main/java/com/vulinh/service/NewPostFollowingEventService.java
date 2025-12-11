package com.vulinh.service;

import com.vulinh.data.event.EventMessageWrapper;
import com.vulinh.data.event.payload.NewPostFollowingEvent;
import com.vulinh.data.mapper.EventMapper;
import com.vulinh.data.repository.NewPostFollowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewPostFollowingEventService extends BaseEventService<NewPostFollowingEvent> {

  final NewPostFollowingRepository newPostFollowingRepository;

  @Override
  protected void processEventInternal(EventMessageWrapper<NewPostFollowingEvent> event) {
    newPostFollowingRepository.save(EventMapper.INSTANCE.toNewPostFollowingEntity(event));
  }
}
