package com.vulinh.data.entity;

import module java.base;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class NewPost extends BaseNexusEvent {

  @Serial private static final long serialVersionUID = 7972890117944414414L;

  // Comment ID is located in the parent class (UUID id)
  // Ctrl + Click

  String title;
  String excerpt;
}
