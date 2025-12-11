package com.vulinh.data.repository;

import module java.base;

import com.vulinh.data.entity.NewPost;
import org.springframework.stereotype.Repository;

@Repository
public interface NewPostRepository extends BaseEventRepository<NewPost, UUID> {}
