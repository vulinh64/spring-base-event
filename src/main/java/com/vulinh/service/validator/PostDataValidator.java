package com.vulinh.service.validator;

import module java.base;

import com.vulinh.data.event.payload.WithPostData;
import com.vulinh.utils.Validators;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Getter
@Accessors(fluent = true)
public enum PostDataValidator implements EventPayloadValidator<WithPostData> {
  POST_VALID_ID(Validators.notNull(WithPostData::postId), "Post ID must not be null"),
  POST_NON_EMPTY_TITLE(Validators.notEmpty(WithPostData::title), "Post title must not be empty"),
  POST_NOT_EMPTY_EXCERPT(
      Validators.notEmpty(WithPostData::excerpt), "Post excerpt must not be empty");

  final Predicate<WithPostData> predicate;
  final String errorMessage;
}
