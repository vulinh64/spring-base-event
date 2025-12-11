package com.vulinh.data;

public enum EventStatus {
  RECEIVED, // First received
  SUCCESS, // Processed successfully
  FAILED, // Processing failed, can retry
  GIVEN_UP // Processing failed, no more retries
}
