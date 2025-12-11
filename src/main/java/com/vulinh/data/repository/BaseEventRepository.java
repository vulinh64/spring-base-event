package com.vulinh.data.repository;

import module java.base;

import com.vulinh.data.entity.BaseAuditableEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseEventRepository<T extends BaseAuditableEvent<I>, I extends Serializable>
    extends JpaRepository<T, I>, JpaSpecificationExecutor<T>, ListQuerydslPredicateExecutor<T> {}
