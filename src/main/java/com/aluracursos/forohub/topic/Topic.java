package com.aluracursos.forohub.topic;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
// import lombok.NoArgsConstructor;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
// @NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String message;
  private Instant createdAt;

  public Topic() {
  }

  public Topic(DataRegisterTopic dataRegisterTopic) {
    this.title = dataRegisterTopic.title();
    this.message = dataRegisterTopic.message();
    this.createdAt = Instant.now();
  }
}
