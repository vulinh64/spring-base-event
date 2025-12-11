package com.vulinh.service;

import com.vulinh.data.event.EventMessageWrapper;
import com.vulinh.data.event.payload.NewSubscriberEvent;
import com.vulinh.data.mapper.EventMapper;
import com.vulinh.data.repository.NewSubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewSubscriberEventService extends BaseEventService<NewSubscriberEvent> {

  final NewSubscriberRepository newSubscriberRepository;

  @Override
  protected void processEventInternal(EventMessageWrapper<NewSubscriberEvent> event) {
    newSubscriberRepository.save(EventMapper.INSTANCE.toNewSubscriberEntity(event));
  }
}
