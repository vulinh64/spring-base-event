package com.vulinh.service;

import com.vulinh.Commons;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.rabbitmq.RabbitMQContainer;

abstract class MessageBrokerBase extends BaseIntegrationTest {

  @Container
  protected static final RabbitMQContainer RABBITMQ = new RabbitMQContainer(Commons.RABBITMQ_IMAGE);

  protected static void propertiesWithRabbitMqAndPostgres(DynamicPropertyRegistry registry) {
    propertiesWithPostgres(registry);

    registry.add("spring.rabbitmq.host", RABBITMQ::getHost);
    registry.add("spring.rabbitmq.port", RABBITMQ::getAmqpPort);
    registry.add("spring.rabbitmq.username", RABBITMQ::getAdminUsername);
    registry.add("spring.rabbitmq.password", RABBITMQ::getAdminPassword);
  }
}
