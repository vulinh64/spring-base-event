package com.vulinh.service;

import com.vulinh.Commons;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.rabbitmq.RabbitMQContainer;

abstract class MessageBrokerBase extends BaseIntegrationTest {

  protected static final RabbitMQContainer RABBITMQ = new RabbitMQContainer(Commons.RABBITMQ_IMAGE);

  static {
    RABBITMQ.start();
  }

  @DynamicPropertySource
  static void propertiesWithRabbitMqAndPostgres(DynamicPropertyRegistry registry) {
    propertiesWithPostgres(registry);

    registry.add("spring.rabbitmq.host", RABBITMQ::getHost);
    registry.add("spring.rabbitmq.port", RABBITMQ::getAmqpPort);
    registry.add("spring.rabbitmq.username", RABBITMQ::getAdminUsername);
    registry.add("spring.rabbitmq.password", RABBITMQ::getAdminPassword);
  }
}
