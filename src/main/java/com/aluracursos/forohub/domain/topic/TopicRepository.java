package com.aluracursos.forohub.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicRepository extends JpaRepository<Topic, Long> {
  Page<Topic> findAllByActiveTrue(Pageable pagination);

  Optional<Topic> findByIdAndActiveTrue(Long id);
}
