package com.aluracursos.forohub.topic;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterTopic(
    @NotBlank String title,
    @NotBlank String message) {

}
