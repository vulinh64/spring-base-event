package com.vulinh.data.repository;

import module java.base;

import com.vulinh.data.entity.BaseEvent;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseEventRepository<T extends BaseEvent<I>, I extends Serializable>
    extends BaseRepository<T, I> {}
