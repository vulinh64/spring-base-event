package com.vulinh.service.scheduler;

import com.vulinh.data.EventStatus;
import com.vulinh.data.entity.NewPost;
import com.vulinh.data.entity.QNewPost;
import com.vulinh.data.repository.NewPostRepository;
import com.vulinh.utils.PredicateBuilder;
import java.util.concurrent.TimeUnit;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.scheduling.annotation.Scheduled;

public interface NewPostEventScheduler {

  int MAX_RETRIES = 5;
  int LIMIT_ENTRIES = 1;
  int PAGE_ZERO = 0;

  NewPostRepository getNewPostRepository();

  void processNewPostEvents(NewPost newPost);

  @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
  default void processNewPostEvent() {
    var qNewPost = QNewPost.newPost;

    getNewPostRepository()
        .findAll(
            qNewPost
                .status
                .notIn(EventStatus.SUCCESS, EventStatus.GIVEN_UP)
                .and(qNewPost.retryCount.loe(MAX_RETRIES)),
            PageRequest.of(
                PAGE_ZERO,
                LIMIT_ENTRIES,
                Sort.by(Order.asc(PredicateBuilder.getFieldName(qNewPost.timestamp)))))
        .stream()
        .findFirst()
        .ifPresent(this::processNewPostEvents);
  }
}
