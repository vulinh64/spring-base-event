package com.vulinh.configuration;

import com.vulinh.data.event.EventMessageWrapper;
import com.vulinh.data.event.NewCommentEvent;
import com.vulinh.data.event.NewPostEvent;
import com.vulinh.data.event.NewSubscriberEvent;
import java.util.function.Consumer;
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
}
