package com.vulinh.data.entity;

import module java.base;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NewPost extends BaseNexusEvent {

  @Serial private static final long serialVersionUID = 7972890117944414414L;

  String title;
  String excerpt;
}
