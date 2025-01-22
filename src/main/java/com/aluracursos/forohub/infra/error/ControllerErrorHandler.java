package com.aluracursos.forohub.infra.error;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerErrorHandler {
  // Error 404
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Void> handleEntityNotFound() {
    return ResponseEntity.notFound().build();
  }

  // Error 400
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    var errors = e.getFieldErrors().stream().map(DataErrorValidation::new).toList();

    return ResponseEntity.badRequest().body(errors);
  }

  private record DataErrorValidation(String field, String error) {
    public DataErrorValidation(FieldError error) {
      this(error.getField(), error.getDefaultMessage());
    }
  }

}
