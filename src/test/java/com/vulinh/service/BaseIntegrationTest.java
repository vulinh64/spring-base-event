package com.vulinh.service;

import com.vulinh.Commons;
import com.vulinh.configuration.ApplicationProperties;
import com.vulinh.service.scheduler.NewPostEventScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mariadb.MariaDBContainer;

// So that the scheduled tasks won't run during tests
@ActiveProfiles("test")
@Testcontainers(parallel = true)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableConfigurationProperties(ApplicationProperties.class)
public abstract class BaseIntegrationTest {

  @Container
  protected static final MariaDBContainer MARIADB = new MariaDBContainer(Commons.MARIADB_IMAGE);

  protected static void propertiesWithMariaDb(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", MARIADB::getJdbcUrl);
    registry.add("spring.datasource.username", MARIADB::getUsername);
    registry.add("spring.datasource.password", MARIADB::getPassword);
  }

  @Autowired protected StreamBridge streamBridge;

  // It works
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  protected ApplicationProperties applicationProperties;

  @MockitoBean NewPostEventScheduler newPostEventScheduler;
}
