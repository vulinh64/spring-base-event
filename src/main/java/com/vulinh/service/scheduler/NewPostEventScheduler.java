package com.vulinh.service.scheduler;

import com.vulinh.data.EventStatus;
import com.vulinh.data.entity.NewPost;
import com.vulinh.data.entity.QNewPost;
import com.vulinh.data.repository.NewPostRepository;
import com.vulinh.utils.QOrderBuilder;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.scheduling.annotation.Scheduled;

public interface NewPostEventScheduler {

  int MAX_RETRIES = 5;
  int LIMIT_ENTRIES = 1;
  int PAGE_ZERO = 0;

  NewPostRepository getNewPostRepository();

  void processNewPostEvents(NewPost newPost);

  @Scheduled(cron = "#{schedulerCron.newPostEventCron()}")
  default void processNewPostEvent() {
    var qNewPost = QNewPost.newPost;

    getNewPostRepository()
        .findAll(
            qNewPost
                .status
                .notIn(EventStatus.SUCCESS, EventStatus.GIVEN_UP)
                .and(qNewPost.retryCount.loe(MAX_RETRIES)),
            QPageRequest.of(PAGE_ZERO, LIMIT_ENTRIES, QOrderBuilder.of(qNewPost.timestamp).asc()))
        .stream()
        .findFirst()
        .ifPresent(this::processNewPostEvents);
  }
}
