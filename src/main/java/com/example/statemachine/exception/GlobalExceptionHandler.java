package com.example.statemachine.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConfigDataResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleResourceNotFoundException(ConfigDataResourceNotFoundException ex, WebRequest request) {
        return createErrorMessage(HttpStatus.NOT_FOUND, ex, request);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public ErrorMessage handleAlreadyUploadedException(RuntimeException ex, WebRequest request) {
        return createErrorMessage(HttpStatus.ALREADY_REPORTED, ex, request);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleHttpClientErrorException(Exception ex, WebRequest request) {
        return createErrorMessage(HttpStatus.CONFLICT, ex, request);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorMessage> handleResponseStatusException(ResponseStatusException ex) {
        ErrorMessage errorResponseDto = new ErrorMessage();
        errorResponseDto.setMessage(ex.getReason());
        errorResponseDto.setStatusCode(ex.getStatusCode().value());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        errorResponseDto.setDescription(ex.getReason());
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(errorResponseDto);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handleMaxUploadSizeExceeded(MaxUploadSizeExceededException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "error", "file.size.exceeded"
                ));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleGlobalException(Exception ex, WebRequest request) {
        return createErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }

    private ErrorMessage createErrorMessage(HttpStatus status, Exception exception, WebRequest request) {
        return new ErrorMessage(
                status.value(),
                exception.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
    }
}
