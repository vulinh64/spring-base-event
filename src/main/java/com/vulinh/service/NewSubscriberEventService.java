package com.vulinh.service;

import module java.base;

import com.vulinh.data.entity.NewSubscriber;
import com.vulinh.data.entity.ids.NewSubscriberId;
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
public class NewSubscriberEventService
    extends BaseEventService<NewSubscriberEvent, NewSubscriber, NewSubscriberId> {

  final NewSubscriberRepository newSubscriberRepository;

  @Override
  protected void ensureValidData(@NonNull NewSubscriberEvent data) {
    Validators.NEW_SUBSCRIBER_VALIDATORS.validate(data);
  }

  @Override
  protected @NonNull NewSubscriberId getEntityId(@NonNull EventMessageWrapper<NewSubscriberEvent> event) {
    return NewSubscriberId.builder()
        .subscribedUserId(event.data().subscribedUserId())
        .actionUserId(event.actionUser().id())
        .build();
  }

  @Override
  protected @NonNull NewSubscriberRepository getRepository() {
    return newSubscriberRepository;
  }

  @Override
  protected @NonNull Function<EventMessageWrapper<NewSubscriberEvent>, NewSubscriber> getEntityConverter() {
    return EventMapper.INSTANCE::toNewSubscriberEntity;
  }
}
