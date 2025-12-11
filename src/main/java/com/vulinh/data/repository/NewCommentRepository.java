package com.vulinh.data.repository;

import module java.base;

import com.vulinh.data.entity.NewComment;
import org.springframework.stereotype.Repository;

@Repository
public interface NewCommentRepository extends BaseEventRepository<NewComment, UUID> {}
