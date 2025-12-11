package com.vulinh.data.mapper;

import com.vulinh.data.entity.*;
import com.vulinh.data.entity.ids.NewPostFollowingId;
import com.vulinh.data.event.*;
import com.vulinh.data.event.payload.NewCommentEvent;
import com.vulinh.data.event.payload.NewPostEvent;
import com.vulinh.data.event.payload.NewPostFollowingEvent;
import com.vulinh.data.event.payload.NewSubscriberEvent;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true), imports = NewPostFollowingId.class)
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
  NewComment toNewCommentEntity(EventMessageWrapper<NewCommentEvent> event);

  @Mapping(target = "createdDateTime", ignore = true)
  @Mapping(target = "updatedDateTime", ignore = true)
  @Mapping(target = "actionUsername", source = "event.actionUser.username")
  @Mapping(target = "actionUserId", source = "event.actionUser.id")
  @Mapping(target = "title", source = "event.data.title")
  @Mapping(target = "excerpt", source = "event.data.excerpt")
  NewPost toNewPostEntity(EventMessageWrapper<NewPostEvent> event);

  @Mapping(target = "status", ignore = true)
  @Mapping(target = "retryCount", ignore = true)
  @Mapping(target = "failureReason", ignore = true)
  @Mapping(target = "createdDateTime", ignore = true)
  @Mapping(target = "updatedDateTime", ignore = true)
  @Mapping(target = "actionUsername", source = "event.actionUser.username")
  @Mapping(
      target = "id",
      expression = "java(new NewPostFollowingId(event.data().postId(), event.actionUser().id()))")
  NewPostFollowing toNewPostFollowingEntity(EventMessageWrapper<NewPostFollowingEvent> event);

  @Mapping(target = "status", ignore = true)
  @Mapping(target = "retryCount", ignore = true)
  @Mapping(target = "failureReason", ignore = true)
  @Mapping(target = "createdDateTime", ignore = true)
  @Mapping(target = "updatedDateTime", ignore = true)
  @Mapping(target = "actionUsername", source = "event.actionUser.username")
  @Mapping(
      target = "id",
      expression =
          "java(new NewSubscriberId(event.data().subscribedUserId(), event.actionUser().id()))")
  NewSubscriber toNewSubscriberEntity(EventMessageWrapper<NewSubscriberEvent> event);
}
