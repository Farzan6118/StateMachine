package com.example.statemachine.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecordStatus {
    INACTIVE,
    ACTIVE,
    DELETED,
}
