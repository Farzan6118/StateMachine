package com.example.statemachine.repository;

import com.example.statemachine.domain.entity.TicketStateHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketStateHistoryRepository extends JpaRepository<TicketStateHistory, Long> {
}

