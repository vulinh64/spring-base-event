package com.vulinh.data.mapper;

import com.vulinh.data.entity.*;
import com.vulinh.data.entity.ids.NewPostFollowingId;
import com.vulinh.data.entity.ids.NewSubscriberId;
import com.vulinh.data.event.*;
import com.vulinh.data.event.payload.NewCommentEvent;
import com.vulinh.data.event.payload.NewPostEvent;
import com.vulinh.data.event.payload.NewPostFollowingEvent;
import com.vulinh.data.event.payload.NewSubscriberEvent;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
    builder = @Builder(disableBuilder = true),
    imports = {NewPostFollowingId.class, NewSubscriberId.class})
public interface EventMapper {

  EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

  @Mapping(target = "createdDateTime", ignore = true)
  @Mapping(target = "updatedDateTime", ignore = true)
  @Mapping(target = "actionUsername", source = "event.actionUser.username")
  @Mapping(target = "actionUserId", source = "event.actionUser.id")
  @Mapping(target = "title", source = "event.data.title")
  @Mapping(target = "postId", source = "event.data.postId")
  @Mapping(target = "excerpt", source = "event.data.excerpt")
  @Mapping(target = "content", source = "event.data.content")
  @Mapping(target = "id", source = "event.data.commentId")
  NewComment toNewCommentEntity(EventMessageWrapper<NewCommentEvent> event);

  @Mapping(target = "createdDateTime", ignore = true)
  @Mapping(target = "updatedDateTime", ignore = true)
  @Mapping(target = "actionUsername", source = "event.actionUser.username")
  @Mapping(target = "actionUserId", source = "event.actionUser.id")
  @Mapping(target = "title", source = "event.data.title")
  @Mapping(target = "excerpt", source = "event.data.excerpt")
  @Mapping(target = "id", source = "event.data.postId")
  NewPost toNewPostEntity(EventMessageWrapper<NewPostEvent> event);

  @Mapping(target = "excerpt", source = "event.data.excerpt")
  @Mapping(target = "title", source = "event.data.title")
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "retryCount", ignore = true)
  @Mapping(target = "failureReason", ignore = true)
  @Mapping(target = "createdDateTime", ignore = true)
  @Mapping(target = "updatedDateTime", ignore = true)
  @Mapping(target = "actionUsername", source = "event.actionUser.username")
  @Mapping(target = "id", expression = "java(toNewPostFollowingId(event))")
  NewPostFollowing toNewPostFollowingEntity(EventMessageWrapper<NewPostFollowingEvent> event);

  @Mapping(target = "subscribedUsername", source = "event.data.subscribedUsername")
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "retryCount", ignore = true)
  @Mapping(target = "failureReason", ignore = true)
  @Mapping(target = "createdDateTime", ignore = true)
  @Mapping(target = "updatedDateTime", ignore = true)
  @Mapping(target = "actionUsername", source = "event.actionUser.username")
  @Mapping(target = "id", expression = "java(toNewSubscriberId(event))")
  NewSubscriber toNewSubscriberEntity(EventMessageWrapper<NewSubscriberEvent> event);

  default NewPostFollowingId toNewPostFollowingId(
      EventMessageWrapper<NewPostFollowingEvent> event) {
    return NewPostFollowingId.builder()
        .postId(event.data().postId())
        .actionUserId(event.actionUser().id())
        .build();
  }

  default NewSubscriberId toNewSubscriberId(EventMessageWrapper<NewSubscriberEvent> event) {
    return NewSubscriberId.builder()
        .subscribedUserId(event.data().subscribedUserId())
        .actionUserId(event.actionUser().id())
        .build();
  }
}
