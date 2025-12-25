package com.vulinh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
// Spring Boot main class should have a public constructor if beans are to be defined later
@SuppressWarnings("java:S1118")
public class SpringBaseEventApplication {

  static void main(String[] args) {
    SpringApplication.run(SpringBaseEventApplication.class, args);
  }
}
