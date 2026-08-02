package com.vulinh.service;

import com.vulinh.Commons;
import com.vulinh.configuration.ApplicationProperties;
import com.vulinh.service.scheduler.NewPostEventScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;

// So that the scheduled tasks won't run during tests
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableConfigurationProperties(ApplicationProperties.class)
public abstract class BaseIntegrationTest {

  protected static final PostgreSQLContainer<?> POSTGRES =
      new PostgreSQLContainer<>(Commons.POSTGRESQL_IMAGE);

  protected static final RabbitMQContainer RABBITMQ =
      new RabbitMQContainer(Commons.RABBITMQ_IMAGE);

  static {
    POSTGRES.start();
    RABBITMQ.start();
  }

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
    registry.add("spring.datasource.username", POSTGRES::getUsername);
    registry.add("spring.datasource.password", POSTGRES::getPassword);
    registry.add("spring.rabbitmq.host", RABBITMQ::getHost);
    registry.add("spring.rabbitmq.port", RABBITMQ::getAmqpPort);
    registry.add("spring.rabbitmq.username", RABBITMQ::getAdminUsername);
    registry.add("spring.rabbitmq.password", RABBITMQ::getAdminPassword);
  }

  @Autowired protected StreamBridge streamBridge;

  @Autowired
  protected ApplicationProperties applicationProperties;

  @MockitoBean NewPostEventScheduler newPostEventScheduler;
}
