package com.vulinh.service;

import com.vulinh.ContainersConfiguration;
import com.vulinh.configuration.ApplicationProperties;
import com.vulinh.service.scheduler.NewPostEventScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

// So that the scheduled tasks won't run during tests
@ActiveProfiles("test")
@SpringBootTest
@EnableConfigurationProperties(ApplicationProperties.class)
@Import(ContainersConfiguration.class)
public abstract class BaseIntegrationTest {

  @Autowired protected StreamBridge streamBridge;

  @Autowired protected ApplicationProperties applicationProperties;

  @MockitoBean NewPostEventScheduler newPostEventScheduler;
}
