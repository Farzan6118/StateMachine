package com.example.statemachine.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private Integer statusCode;
    private String message;
    private String description;
    private LocalDateTime timestamp;

}
