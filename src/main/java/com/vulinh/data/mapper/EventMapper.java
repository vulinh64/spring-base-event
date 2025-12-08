package com.vulinh.data.mapper;

import com.vulinh.data.entity.NewComment;
import com.vulinh.data.event.EventMessageWrapper;
import com.vulinh.data.event.NewCommentEvent;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(builder = @Builder(disableBuilder = true))
public interface EventMapper {

  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "actionUsername", source = "newCommentEvent.actionUser.username")
  @Mapping(target = "actionUserId", source = "newCommentEvent.actionUser.id")
  @Mapping(target = "postId", source = "newCommentEvent.data.postId")
  @Mapping(target = "title", source = "newCommentEvent.data.title")
  @Mapping(target = "content", source = "newCommentEvent.data.content")
  NewComment toNewCommentEntity(EventMessageWrapper<NewCommentEvent> newCommentEvent);
}
