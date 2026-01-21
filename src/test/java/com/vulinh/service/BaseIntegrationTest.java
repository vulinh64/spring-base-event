package com.vulinh.service;

import com.vulinh.configuration.TestApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mariadb.MariaDBContainer;
import org.testcontainers.rabbitmq.RabbitMQContainer;

// So that the scheduled tasks won't run during tests
@ActiveProfiles({"test", "production"})
@Testcontainers(parallel = true)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableConfigurationProperties(TestApplicationProperties.class)
public abstract class BaseIntegrationTest {

  @Container
  protected static final RabbitMQContainer RABBITMQ =
      new RabbitMQContainer("rabbitmq:4.2.1-alpine");

  @Container
  protected static final MariaDBContainer MARIADB = new MariaDBContainer("mariadb:12.1.2-noble");

  protected static void setPropertiesInternal(DynamicPropertyRegistry registry) {
    registry.add("spring.rabbitmq.host", RABBITMQ::getHost);
    registry.add("spring.rabbitmq.port", RABBITMQ::getAmqpPort);
    registry.add("spring.rabbitmq.username", RABBITMQ::getAdminUsername);
    registry.add("spring.rabbitmq.password", RABBITMQ::getAdminPassword);
    registry.add("spring.datasource.url", MARIADB::getJdbcUrl);
    registry.add("spring.datasource.username", MARIADB::getUsername);
    registry.add("spring.datasource.password", MARIADB::getPassword);
  }

  @Autowired protected StreamBridge streamBridge;

  // It works
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  protected TestApplicationProperties testApplicationProperties;
}
