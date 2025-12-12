package com.vulinh.configuration;

import module java.base;

import com.vulinh.data.entity.NewComment;
import com.vulinh.data.entity.NewPost;
import com.vulinh.data.entity.NewPostFollowing;
import com.vulinh.data.entity.NewSubscriber;
import com.vulinh.data.entity.ids.NewPostFollowingId;
import com.vulinh.data.entity.ids.NewSubscriberId;
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
      BaseEventService<NewCommentEvent, NewComment, UUID> newCommentEventService) {
    return newCommentEventService::processEvent;
  }

  @Bean
  public Consumer<EventMessageWrapper<NewPostEvent>> newPost(
      BaseEventService<NewPostEvent, NewPost, UUID> newPostEventService) {
    return newPostEventService::processEvent;
  }

  @Bean
  public Consumer<EventMessageWrapper<NewPostFollowingEvent>> newPostFollowing(
      BaseEventService<NewPostFollowingEvent, NewPostFollowing, NewPostFollowingId>
          newPostFollowingEventService) {
    return newPostFollowingEventService::processEvent;
  }

  @Bean
  public Consumer<EventMessageWrapper<NewSubscriberEvent>> newSubscriber(
      BaseEventService<NewSubscriberEvent, NewSubscriber, NewSubscriberId>
          newSubscriberEventService) {
    return newSubscriberEventService::processEvent;
  }
}
