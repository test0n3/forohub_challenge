package com.aluracursos.forohub.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.aluracursos.forohub.domain.topic.DataDetailTopic;
import com.aluracursos.forohub.domain.topic.DataListTopic;
import com.aluracursos.forohub.domain.topic.DataRegisterTopic;
import com.aluracursos.forohub.domain.topic.DataUpdateTopic;
import com.aluracursos.forohub.domain.topic.Topic;
import com.aluracursos.forohub.domain.topic.TopicRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicController {
  @Autowired
  private TopicRepository topicRepository;

  @GetMapping
  public ResponseEntity<Page<DataListTopic>> listTopics(
      @PageableDefault(size = 10, page = 0, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pagination) {
    return ResponseEntity.ok(topicRepository.findAllByActiveTrue(pagination).map(DataListTopic::new));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataDetailTopic> getTopicById(@PathVariable Long id) {
    Topic topic = topicRepository.findByIdAndActiveTrue(id);
    if (topic == null) {
      return ResponseEntity.notFound().build();
    } else {
      DataDetailTopic dataDetailTopic = new DataDetailTopic(topic);
      return ResponseEntity.ok(dataDetailTopic);
    }
  }

  @PostMapping
  @Transactional
  public ResponseEntity<DataDetailTopic> registerTopic(@RequestBody @Valid DataRegisterTopic dataRegisterTopic,
      UriComponentsBuilder uriComponentsBuilder) {
    Topic topic = topicRepository.save(new Topic(dataRegisterTopic));
    DataDetailTopic payload = new DataDetailTopic(topic);
    URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
    return ResponseEntity.created(url).body(payload);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<DataDetailTopic> updateTopic(@RequestBody @Valid DataUpdateTopic dataUpdateTopic) {
    Topic topic = topicRepository.findByIdAndActiveTrue(dataUpdateTopic.id());

    if (topic == null) {
      return ResponseEntity.notFound().build();
    }

    topic.updateTopic(dataUpdateTopic);
    return ResponseEntity.ok(new DataDetailTopic(topic));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
    Topic topic = topicRepository.getReferenceById(id);
    topic.deactivateTopic();
    return ResponseEntity.noContent().build();
  }
}
