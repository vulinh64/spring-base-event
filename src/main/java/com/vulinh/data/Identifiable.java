package com.vulinh.data;

import module java.base;

public interface Identifiable<T extends Serializable> {

  T getId();
}
