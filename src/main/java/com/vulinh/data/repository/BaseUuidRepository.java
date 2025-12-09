package com.vulinh.data.repository;

import module java.base;

import com.vulinh.data.entity.BaseEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseUuidRepository<T extends BaseEventEntity>
    extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T>, ListQuerydslPredicateExecutor<T> {}
