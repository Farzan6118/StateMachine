package com.example.statemachine.domain.entity;

import com.example.statemachine.domain.enums.Events;
import com.example.statemachine.domain.enums.States;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketStateHistory extends BaseEntity<Long> {

    @Column(nullable = false)
    private Long ticketId;

    @Enumerated(EnumType.STRING)
    private States fromState;

    @Enumerated(EnumType.STRING)
    private States toState;

    @Enumerated(EnumType.STRING)
    private Events event;
}