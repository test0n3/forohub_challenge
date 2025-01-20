package com.aluracursos.forohub.topic;

import java.time.Instant;

public record DataListTopic(
    Long id,
    String title,
    String message,
    Instant createdAt) {
  public DataListTopic(Topic topic) {
    this(
        topic.getId(),
        topic.getTitle(),
        topic.getMessage(),
        topic.getCreatedAt());
  }
}
