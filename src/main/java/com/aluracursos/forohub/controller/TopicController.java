package com.aluracursos.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluracursos.forohub.topic.DataListTopic;
import com.aluracursos.forohub.topic.DataRegisterTopic;
import com.aluracursos.forohub.topic.Topic;
import com.aluracursos.forohub.topic.TopicRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicController {
  @Autowired
  private TopicRepository topicRepository;

  @GetMapping
  public Page<DataListTopic> listTopics(@PageableDefault(size = 2) Pageable pagination) {
    return topicRepository.findAll(pagination).map(DataListTopic::new);
  }

  @PostMapping
  @Transactional
  public void registerTopic(@RequestBody @Valid DataRegisterTopic dataRegisterTopic) {
    // System.out.println("Registrando un nuevo topico: " + dataRegisterTopic);
    topicRepository.save(new Topic(dataRegisterTopic));
  }
}
