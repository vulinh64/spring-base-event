package com.vulinh.configuration;

import module java.base;

import com.vulinh.data.event.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MessagingConfiguration {

  @Bean
  public Consumer<EventMessageWrapper<NewPostEvent>> newPost() {
    return event -> log.info("New post event received: {}", event);
  }

  @Bean
  public Consumer<EventMessageWrapper<NewCommentEvent>> newComment() {
    return event -> log.info("New comment event received: {}", event);
  }

  @Bean
  public Consumer<EventMessageWrapper<NewSubscriberEvent>> newSubscriber() {
    return event -> log.info("New subscriber event received: {}", event);
  }

  @Bean
  public Consumer<EventMessageWrapper<NewPostFollowingEvent>> newPostFollowing() {
    return event -> log.info("New post following event received: {}", event);
  }
}
