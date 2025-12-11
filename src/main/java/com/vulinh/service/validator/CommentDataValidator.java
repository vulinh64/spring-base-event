package com.vulinh.service.validator;

import module java.base;

import com.vulinh.data.event.payload.WithCommentData;
import com.vulinh.utils.Validators;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Getter
@Accessors(fluent = true)
public enum CommentDataValidator implements EventPayloadValidator<WithCommentData> {
  COMMENT_VALID_ID(Validators.notNull(WithCommentData::commentId), "Comment ID must not be null"),
  COMMENT_VALID_CONTENT(
      Validators.notEmpty(WithCommentData::content), "Comment content must not be empty");

  final Predicate<WithCommentData> predicate;
  final String errorMessage;
}
