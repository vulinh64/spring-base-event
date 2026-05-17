package com.vulinh.service;

import static org.junit.jupiter.api.Assertions.*;

import module java.base;

import com.vulinh.data.EventStatus;
import com.vulinh.data.entity.NewComment;
import com.vulinh.data.entity.NewSubscriber;
import com.vulinh.data.repository.NewCommentRepository;
import com.vulinh.data.repository.NewSubscriberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
class JpaSliceTest extends BaseIntegrationTest {

  @Autowired NewCommentRepository newCommentRepository;

  @Autowired NewSubscriberRepository newSubscriberRepository;

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
    var entity =
        newSubscriberRepository.save(
            NewSubscriber.builder()
                .actionUserId(UUID.randomUUID())
                .subscribedUserId(UUID.randomUUID())
                .eventId(UUID.randomUUID())
                .actionUsername("user1")
                .subscribedUsername("user2")
                .timestamp(Instant.now())
                .build());

    assertNotNull(entity.getTimestamp());
    assertNotNull(entity.getEventId());
    assertNotNull(entity.getCreatedDateTime());
    assertNotNull(entity.getUpdatedDateTime());

    var id = entity.getId();

    assertNotNull(id);
    assertNotNull(id.actionUserId());
    assertNotNull(id.subscribedUserId());

    assertEquals("user1", entity.getActionUsername());
    assertEquals("user2", entity.getSubscribedUsername());
  }
}
