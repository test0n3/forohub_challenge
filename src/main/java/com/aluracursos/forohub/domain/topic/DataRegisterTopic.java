package com.aluracursos.forohub.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterTopic(
        @NotBlank(message = "Título es obligatorio") String title,
        @NotBlank(message = "Mensaje es obligatorio") String message) {

}
