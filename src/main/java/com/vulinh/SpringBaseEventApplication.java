package com.vulinh;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringBaseEventApplication {

  static void main(String[] args) {
    SpringApplication.run(SpringBaseEventApplication.class, args);
  }
}
