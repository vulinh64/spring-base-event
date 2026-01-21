package com.vulinh.service.scheduler;

import module java.base;

import com.vulinh.data.EventStatus;
import com.vulinh.data.entity.NewPost;
import com.vulinh.data.entity.QNewSubscriber;
import com.vulinh.data.repository.NewPostRepository;
import com.vulinh.data.repository.NewSubscriberRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile({"!production", "!test"})
public class DevBroadcastNewPostScheduler implements NewPostEventScheduler {

  @Getter final NewPostRepository newPostRepository;

  final NewSubscriberRepository newSubscriberRepository;

  @PostConstruct
  public void info() {
    log.info("Scheduled task {} is enabled", getClass().getSimpleName());
  }

  @Override
  public void processNewPostEvents(NewPost post) {
    var qNewSubscriber = QNewSubscriber.newSubscriber;

    var validSubscribersData =
        newSubscriberRepository.findAll(
            qNewSubscriber
                .id
                .subscribedUserId
                .eq(post.getActionUserId())
                .and(qNewSubscriber.timestamp.lt(post.getTimestamp())));

    if (CollectionUtils.isNotEmpty(validSubscribersData)) {
      log.info("Broadcasting size: {}", validSubscribersData.size());

      for (int i = 0; i < validSubscribersData.size(); i++) {
        var data = validSubscribersData.get(i);
        log.info(
            "Notifying subscriber #{}: ID = [{}] (username [{}]) about new post (ID = [{}]): [{}] (from user [{}])",
            i + 1,
            data.getId().actionUserId(),
            data.getActionUsername(),
            post.getId(),
            post.getTitle(),
            data.getSubscribedUsername());
      }
    }

    newPostRepository.save(post.setStatus(EventStatus.SUCCESS));
  }
}
