package com.vulinh.data.repository;

import module java.base;

import com.vulinh.data.base.AbstractTimestampAuditableEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseEventRepository<
        T extends AbstractTimestampAuditableEntity<I>, I extends Serializable>
    extends BaseRepository<T, I> {}
