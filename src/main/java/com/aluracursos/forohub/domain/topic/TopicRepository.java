package com.aluracursos.forohub.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicRepository extends JpaRepository<Topic, Long> {
  Page<Topic> findAllByActiveTrue(Pageable pagination);

  Topic findByIdAndActiveTrue(Long id);
}
