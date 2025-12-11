package com.vulinh.configuration;

import module java.base;

import com.vulinh.data.event.*;
import com.vulinh.data.event.payload.NewCommentEvent;
import com.vulinh.data.event.payload.NewPostEvent;
import com.vulinh.data.event.payload.NewPostFollowingEvent;
import com.vulinh.data.event.payload.NewSubscriberEvent;
import com.vulinh.service.BaseEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MessagingConfiguration {

  @Bean
  public Consumer<EventMessageWrapper<NewCommentEvent>> newComment(
      BaseEventService<NewCommentEvent> newCommentEventService) {
    return newCommentEventService::processEvent;
  }

  @Bean
  public Consumer<EventMessageWrapper<NewPostEvent>> newPost(
      BaseEventService<NewPostEvent> newPostEventService) {
    return newPostEventService::processEvent;
  }

  @Bean
  public Consumer<EventMessageWrapper<NewPostFollowingEvent>> newPostFollowing(
      BaseEventService<NewPostFollowingEvent> newPostFollowingEventService) {
    return newPostFollowingEventService::processEvent;
  }

  @Bean
  public Consumer<EventMessageWrapper<NewSubscriberEvent>> newSubscriber(
      BaseEventService<NewSubscriberEvent> newSubscriberEventService) {
    return newSubscriberEventService::processEvent;
  }
}
