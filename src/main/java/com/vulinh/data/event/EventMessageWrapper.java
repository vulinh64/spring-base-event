package com.vulinh.data.event;

import module java.base;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record EventMessageWrapper<T>(
    UUID eventId, Instant timestamp, EventType eventType, ActionUser actionUser, T data)
    implements BaseEvent {}
