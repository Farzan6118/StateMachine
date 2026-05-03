package com.example.statemachine.domain.entity;

import com.example.statemachine.domain.enums.States;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket extends BaseEntity<Long> {

    @Enumerated(EnumType.STRING)
    private States state;

}