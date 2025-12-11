package com.vulinh.utils;

import module java.base;

import com.vulinh.data.event.payload.NewSubscriberEvent;
import com.vulinh.data.event.payload.WithCommentData;
import com.vulinh.data.event.payload.WithPostData;
import com.vulinh.data.exception.ValidationException;
import com.vulinh.service.validator.CommentDataValidator;
import com.vulinh.service.validator.EventPayloadValidator;
import com.vulinh.service.validator.NewSubscriberEventValidator;
import com.vulinh.service.validator.PostDataValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validators {

  public static final ChainedValidators<WithPostData> POST_VALIDATORS =
      ChainedValidators.of(PostDataValidator.values());

  public static final ChainedValidators<WithCommentData> COMMENT_VALIDATORS =
      ChainedValidators.of(CommentDataValidator.values());

  public static final ChainedValidators<NewSubscriberEvent> NEW_SUBSCRIBER_VALIDATORS =
      ChainedValidators.of(NewSubscriberEventValidator.values());

  public record ChainedValidators<T>(List<EventPayloadValidator<T>> validators) {

    @SafeVarargs
    public static <T> ChainedValidators<T> of(EventPayloadValidator<T>... validators) {
      return new ChainedValidators<>(validators);
    }

    public ChainedValidators {
      validators = validators == null ? Collections.emptyList() : List.copyOf(validators);
    }

    @SafeVarargs
    public ChainedValidators(EventPayloadValidator<T>... validators) {
      Objects.requireNonNull(validators);

      if (ObjectUtils.anyNull((Object[]) validators)) {
        throw new IllegalArgumentException("Validators must not contain null implementations");
      }

      this(new ArrayList<>(Arrays.asList(validators)));
    }

    public void validate(T payload) {
      for (var validator : validators) {
        if (!validator.isValid(payload)) {
          throw new ValidationException(validator.errorMessage());
        }
      }
    }
  }

  public static <T, U> Predicate<T> notNull(Function<T, U> extractor) {
    return data -> extractor.apply(data) != null;
  }

  public static <T> Predicate<T> notEmpty(Function<T, String> extractor) {
    return data -> StringUtils.isNotBlank(extractor.apply(data));
  }
}
