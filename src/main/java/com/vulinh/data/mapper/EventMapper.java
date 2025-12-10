package com.vulinh.data.mapper;

import com.vulinh.data.entity.*;
import com.vulinh.data.event.EventMessageWrapper;
import com.vulinh.data.event.NewCommentEvent;
import com.vulinh.data.event.NewPostEvent;
import com.vulinh.data.event.NewSubscriberEvent;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true), imports = EventStatus.class)
public interface EventMapper {

  EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

  @Mapping(target = "status", expression = "java(EventStatus.RECEIVED)")
  @Mapping(target = "retryCount", ignore = true)
  @Mapping(target = "lastProcessedDate", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "actionUsername", source = "event.actionUser.username")
  @Mapping(target = "actionUserId", source = "event.actionUser.id")
  @Mapping(target = "postId", source = "event.data.postId")
  @Mapping(target = "title", source = "event.data.title")
  @Mapping(target = "content", source = "event.data.content")
  NewComment toNewCommentEntity(EventMessageWrapper<NewCommentEvent> event);

  @Mapping(target = "status", expression = "java(EventStatus.RECEIVED)")
  @Mapping(target = "retryCount", ignore = true)
  @Mapping(target = "lastProcessedDate", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "actionUsername", source = "event.actionUser.username")
  @Mapping(target = "actionUserId", source = "event.actionUser.id")
  @Mapping(target = "title", source = "event.data.title")
  @Mapping(target = "postId", source = "event.data.postId")
  @Mapping(target = "excerpt", source = "event.data.excerpt")
  NewPost toNewPostEntity(EventMessageWrapper<NewPostEvent> event);

  @Mapping(target = "status", expression = "java(EventStatus.RECEIVED)")
  @Mapping(target = "retryCount", ignore = true)
  @Mapping(target = "lastProcessedDate", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "postId", source = "event.data.postId")
  @Mapping(target = "actionUsername", source = "event.actionUser.username")
  @Mapping(target = "actionUserId", source = "event.actionUser.id")
  NewPostFollowing toNewPostFollowingEntity(EventMessageWrapper<NewPostEvent> event);

  @Mapping(target = "status", expression = "java(EventStatus.RECEIVED)")
  @Mapping(target = "retryCount", ignore = true)
  @Mapping(target = "lastProcessedDate", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "subscribedUsername", source = "event.data.subscribedUsername")
  @Mapping(target = "subscribedUserId", source = "event.data.subscribedUserId")
  @Mapping(target = "actionUsername", source = "event.actionUser.username")
  @Mapping(target = "actionUserId", source = "event.actionUser.id")
  NewSubscriber toNewSubscriberEntity(EventMessageWrapper<NewSubscriberEvent> event);
}
