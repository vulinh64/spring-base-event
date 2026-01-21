package com.vulinh.service;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import module java.base;

import com.vulinh.data.entity.QNewSubscriber;
import com.vulinh.data.entity.ids.NewSubscriberId;
import com.vulinh.data.event.ActionUser;
import com.vulinh.data.event.EventMessageWrapper;
import com.vulinh.data.event.EventType;
import com.vulinh.data.event.payload.NewSubscriberEvent;
import com.vulinh.data.repository.NewSubscriberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

class NewSubscriberEventServiceIT extends BaseIntegrationTest {

  static final UUID SUBSCRIBING_USER_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");
  static final UUID SUBSCRIBED_USER_ID = UUID.fromString("22222222-2222-2222-2222-222222222222");
  static final String SUBSCRIBING_USERNAME = "subscribingUser";
  static final String SUBSCRIBED_USERNAME = "subscribedUser";

  @Autowired NewSubscriberRepository newSubscriberRepository;

  @DynamicPropertySource
  static void registerProperties(DynamicPropertyRegistry registry) {
    setPropertiesInternal(registry);
  }

  @Test
  void testNewSubscriberEvent() {
    var eventWrapper =
        EventMessageWrapper.<NewSubscriberEvent>builder()
            .eventType(EventType.NEW_SUBSCRIBER)
            .actionUser(
                ActionUser.builder().username(SUBSCRIBING_USERNAME).id(SUBSCRIBING_USER_ID).build())
            .data(
                NewSubscriberEvent.builder()
                    .subscribedUsername(SUBSCRIBED_USERNAME)
                    .subscribedUserId(SUBSCRIBED_USER_ID)
                    .build())
            .build();

    streamBridge.send(
        testApplicationProperties.messageTopic().newSubscriber().topicName(), eventWrapper);

    var id =
        NewSubscriberId.builder()
            .actionUserId(SUBSCRIBING_USER_ID)
            .subscribedUserId(SUBSCRIBED_USER_ID)
            .build();

    await()
        .atMost(Duration.ofSeconds(10))
        .pollInterval(Duration.ofSeconds(2))
        .untilAsserted(
            () ->
                assertTrue(newSubscriberRepository.exists(QNewSubscriber.newSubscriber.id.eq(id))));

    var entity = newSubscriberRepository.findById(id).orElseThrow();

    assertEquals(SUBSCRIBING_USERNAME, entity.getActionUsername());
    assertEquals(SUBSCRIBED_USERNAME, entity.getSubscribedUsername());
  }
}
