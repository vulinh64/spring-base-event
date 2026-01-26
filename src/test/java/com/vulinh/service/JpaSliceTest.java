package com.vulinh.service;

import static org.junit.jupiter.api.Assertions.*;

import module java.base;

import com.vulinh.Commons;
import com.vulinh.data.EventStatus;
import com.vulinh.data.entity.NewComment;
import com.vulinh.data.entity.NewSubscriber;
import com.vulinh.data.repository.NewCommentRepository;
import com.vulinh.data.repository.NewSubscriberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mariadb.MariaDBContainer;

@DataJpaTest
@Testcontainers
class JpaSliceTest {

  @Container static final MariaDBContainer MARIADB = new MariaDBContainer(Commons.MARIADB_IMAGE);

  @Autowired NewCommentRepository newCommentRepository;

  @Autowired NewSubscriberRepository newSubscriberRepository;

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", MARIADB::getJdbcUrl);
    registry.add("spring.datasource.username", MARIADB::getUsername);
    registry.add("spring.datasource.password", MARIADB::getPassword);
  }

  @Test
  void testNewCommentCreation() {
    var data =
        newCommentRepository.save(
            NewComment.builder()
                .id(UUID.randomUUID())
                .content("Content")
                .postId(UUID.randomUUID())
                .title("Title")
                .excerpt("Excerpt")
                .actionUserId(UUID.randomUUID())
                .actionUsername("randomUser")
                .timestamp(Instant.now())
                .eventId(UUID.randomUUID())
                .build());

    assertNotNull(data.getId());
    assertEquals("Content", data.getContent());
    assertNotNull(data.getPostId());
    assertEquals("Title", data.getTitle());
    assertEquals("Excerpt", data.getExcerpt());
    assertNotNull(data.getActionUserId());
    assertEquals(EventStatus.RECEIVED, data.getStatus());
    assertEquals("randomUser", data.getActionUsername());
    assertNotNull(data.getTimestamp());
    assertNotNull(data.getEventId());
    assertEquals(0, data.getRetryCount());
    assertNotNull(data.getCreatedDateTime());
    assertNotNull(data.getUpdatedDateTime());
    assertNull(data.getFailureReason());
  }

  @Test
  void testNewSubscriberCreation() {
    NewSubscriber build =
        NewSubscriber.builder()
            .actionUserId(UUID.randomUUID())
            .subscribedUserId(UUID.randomUUID())
            .eventId(UUID.randomUUID())
            .actionUsername("user1")
            .subscribedUsername("user2")
            .timestamp(Instant.now())
            .build();

    var data = newSubscriberRepository.save(build);

    assertNotNull(data.getTimestamp());
    assertNotNull(data.getEventId());
    assertNotNull(data.getCreatedDateTime());
    assertNotNull(data.getUpdatedDateTime());

    var id = data.getId();

    assertNotNull(id);
    assertNotNull(id.actionUserId());
    assertNotNull(id.subscribedUserId());

    assertEquals("user1", data.getActionUsername());
    assertEquals("user2", data.getSubscribedUsername());
  }
}
