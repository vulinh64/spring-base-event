package com.vulinh.service;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.vulinh.configuration.TestApplicationProperties;
import com.vulinh.data.entity.ids.NewPostFollowingId;
import com.vulinh.data.event.ActionUser;
import com.vulinh.data.event.EventMessageWrapper;
import com.vulinh.data.event.EventType;
import com.vulinh.data.event.payload.NewPostFollowingEvent;
import com.vulinh.data.repository.NewPostFollowingRepository;
import java.time.Duration;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mariadb.MariaDBContainer;
import org.testcontainers.rabbitmq.RabbitMQContainer;

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableConfigurationProperties(TestApplicationProperties.class)
class NewPostFollowingEventServiceIT {

  static final UUID POST_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");
  static final UUID ACTION_USER_ID = UUID.fromString("22222222-2222-2222-2222-222222222222");
  static final String TITLE = "title-from-event";
  static final String EXCERPT = "excerpt-from-event";

  @Container
  static final RabbitMQContainer RABBITMQ = new RabbitMQContainer("rabbitmq:4.2.1-alpine");

  @Container static final MariaDBContainer MARIADB = new MariaDBContainer("mariadb:12.1.2-noble");

  @DynamicPropertySource
  static void registerProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.rabbitmq.host", RABBITMQ::getHost);
    registry.add("spring.rabbitmq.port", RABBITMQ::getAmqpPort);
    registry.add("spring.rabbitmq.username", RABBITMQ::getAdminUsername);
    registry.add("spring.rabbitmq.password", RABBITMQ::getAdminPassword);
    registry.add("spring.datasource.url", MARIADB::getJdbcUrl);
    registry.add("spring.datasource.username", MARIADB::getUsername);
    registry.add("spring.datasource.password", MARIADB::getPassword);
  }

  @Autowired StreamBridge streamBridge;

  @Autowired NewPostFollowingRepository newPostFollowingRepository;

  // It works
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  TestApplicationProperties testApplicationProperties;

  @Test
  void testPersistedDataMatchesEvent() {
    // Given
    var wrapper =
        EventMessageWrapper.<NewPostFollowingEvent>builder()
            .eventType(EventType.NEW_POST_FOLLOWING)
            .actionUser(ActionUser.builder().id(ACTION_USER_ID).username("testuser").build())
            .data(
                NewPostFollowingEvent.builder()
                    .postId(POST_ID)
                    .title(TITLE)
                    .excerpt(EXCERPT)
                    .build())
            .build();

    // When
    streamBridge.send(testApplicationProperties.messageTopic().newPostFollowing().topicName(), wrapper);

    // Then: wait for persistence and verify data
    var entityId =
        NewPostFollowingId.builder().postId(POST_ID).actionUserId(ACTION_USER_ID).build();

    await()
        .pollDelay(Duration.ofSeconds(2))
        .atMost(Duration.ofSeconds(10))
        .untilAsserted(() -> assertTrue(newPostFollowingRepository.existsById(entityId)));

    var entity = newPostFollowingRepository.findById(entityId).orElseThrow();

    var id = entity.getId();

    assertEquals(entityId, id);
    assertEquals(POST_ID, id.postId());
    assertEquals(ACTION_USER_ID, id.actionUserId());
    assertEquals(TITLE, entity.getTitle());
    assertEquals(EXCERPT, entity.getExcerpt());
  }
}
