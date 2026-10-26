package com.playpadel.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ApiExceptionHandler {

    // Errors que llancem nosaltres: 401, 403, 409...
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiError> errorAmbEstat(ResponseStatusException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
        ApiError cos = new ApiError(status.value(), status.getReasonPhrase(), ex.getReason());
        return ResponseEntity.status(status).body(cos);
    }

    // Errors de validació (@Valid): retorna el primer missatge
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> errorDeValidacio(MethodArgumentNotValidException ex) {
        String missatge = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .orElse("Dades no vàlides");
        return ResponseEntity.badRequest().body(new ApiError(400, "Bad Request", missatge));
    }

    // JSON mal format o camps amb un tipus incorrecte
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> jsonIncorrecte(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest()
                .body(new ApiError(400, "Bad Request", "El format de les dades no és correcte"));
    }
}