package com.voucherturistico.payment.infrastructure.exceptions;

import com.voucherturistico.payment.domain.exceptions.ExternalException;
import com.voucherturistico.payment.domain.exceptions.StandardError;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidationExceptions(MethodArgumentNotValidException exception) {
        String errorMessage = exception.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.joining(" | "));

        StandardError standardError = StandardError
            .builder()
            .message(errorMessage)
            .timestamp(Instant.now())
            .status(400)
            .build();

        return ResponseEntity.status(HttpStatus.valueOf(400))
            .body(standardError);
    }

    @ExceptionHandler(ExternalException.class)
    public ResponseEntity<StandardError> handleExternalExceptions(ExternalException exception) {
        StandardError standardError = StandardError
            .builder()
            .message(exception.getMessage())
            .timestamp(Instant.now())
            .status(exception.getStatusCode())
            .build();

        return ResponseEntity.status(HttpStatus.valueOf(exception.getStatusCode()))
            .body(standardError);
    }

}
