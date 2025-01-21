package com.aluracursos.forohub.domain.topic;

import jakarta.validation.constraints.NotNull;

public record DataUpdateTopic(
    @NotNull Long id,
    String title,
    String message) {

}
