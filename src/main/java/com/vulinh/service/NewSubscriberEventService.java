package com.vulinh.service;

import com.vulinh.data.event.EventMessageWrapper;
import com.vulinh.data.event.payload.NewSubscriberEvent;
import com.vulinh.data.mapper.EventMapper;
import com.vulinh.data.repository.NewSubscriberRepository;
import com.vulinh.utils.Validators;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewSubscriberEventService extends BaseEventService<NewSubscriberEvent> {

  final NewSubscriberRepository newSubscriberRepository;

  @Override
  protected void ensureValidData(@NonNull NewSubscriberEvent data) {
    Validators.NEW_SUBSCRIBER_VALIDATORS.validate(data);
  }

  @Override
  protected void processEventInternal(EventMessageWrapper<NewSubscriberEvent> event) {
    newSubscriberRepository.save(EventMapper.INSTANCE.toNewSubscriberEntity(event));
  }
}
