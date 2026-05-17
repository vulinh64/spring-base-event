package com.vulinh.data.repository;

import com.vulinh.data.entity.NewSubscriber;
import com.vulinh.data.entity.NewSubscriber.NewSubscriberId;
import org.springframework.stereotype.Repository;

@Repository
public interface NewSubscriberRepository
    extends BaseEventRepository<NewSubscriber, NewSubscriberId> {}
