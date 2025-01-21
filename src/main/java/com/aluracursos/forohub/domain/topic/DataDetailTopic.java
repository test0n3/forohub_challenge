package com.aluracursos.forohub.domain.topic;

import java.time.Instant;

public record DataDetailTopic(
    Long id,
    String title,
    String message,
    Instant createdAt) {

  public DataDetailTopic(Topic topic) {
    this(
        topic.getId(),
        topic.getTitle(),
        topic.getMessage(),
        topic.getCreatedAt());
  }
}
