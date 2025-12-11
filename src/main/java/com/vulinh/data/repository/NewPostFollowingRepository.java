package com.vulinh.data.repository;

import com.vulinh.data.entity.NewPostFollowing;
import com.vulinh.data.entity.ids.NewPostFollowingId;
import org.springframework.stereotype.Repository;

@Repository
public interface NewPostFollowingRepository
    extends BaseEventRepository<NewPostFollowing, NewPostFollowingId> {}
