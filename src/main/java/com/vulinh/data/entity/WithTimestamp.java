package com.vulinh.data.entity;

import module java.base;

public interface WithTimestamp<T extends Temporal> {

  T getTimestamp();
}
