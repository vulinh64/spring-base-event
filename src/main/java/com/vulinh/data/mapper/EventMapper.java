package com.vulinh.data.mapper;

import com.vulinh.data.entity.NewComment;
import com.vulinh.data.entity.NewPost;
import com.vulinh.data.entity.NewPostFollowing;
import com.vulinh.data.entity.NewSubscriber;
import com.vulinh.data.event.EventMessageWrapper;
import com.vulinh.data.event.NewCommentEvent;
import com.vulinh.data.event.NewPostEvent;
import com.vulinh.data.event.NewSubscriberEvent;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(builder = @Builder(disableBuilder = true))
public interface EventMapper {

  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "actionUsername", source = "event.actionUser.username")
  @Mapping(target = "actionUserId", source = "event.actionUser.id")
  @Mapping(target = "postId", source = "event.data.postId")
  @Mapping(target = "title", source = "event.data.title")
  @Mapping(target = "content", source = "event.data.content")
  NewComment toNewCommentEntity(EventMessageWrapper<NewCommentEvent> event);

  @Mapping(target = "title", source = "event.data.title")
  @Mapping(target = "postId", source = "event.data.postId")
  @Mapping(target = "excerpt", source = "event.data.excerpt")
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "actionUsername", source = "event.actionUser.username")
  @Mapping(target = "actionUserId", source = "event.actionUser.id")
  NewPost toNewPostEntity(EventMessageWrapper<NewPostEvent> event);

  @Mapping(target = "postId", source = "event.data.postId")
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "actionUsername", source = "event.actionUser.username")
  @Mapping(target = "actionUserId", source = "event.actionUser.id")
  NewPostFollowing toNewPostFollowingEntity(EventMessageWrapper<NewPostEvent> event);

  @Mapping(target = "subscribedUsername", source = "event.data.subscribedUsername")
  @Mapping(target = "subscribedUserId", source = "event.data.subscribedUserId")
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "actionUsername", source = "event.actionUser.username")
  @Mapping(target = "actionUserId", source = "event.actionUser.id")
  NewSubscriber toNewSubscriberEntity(EventMessageWrapper<NewSubscriberEvent> event);
}
