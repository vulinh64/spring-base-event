package com.vulinh.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application-properties")
public record TestApplicationProperties(MessageTopic messageTopic) {

  public record MessageTopic(TopicProperties newPostFollowing, TopicProperties newSubscriber) {}

  public record TopicProperties(String topicName) {}
}
