package com.example.statemachine.service;

import com.example.statemachine.controller.dto.response.TicketResponseDto;
import com.example.statemachine.domain.enums.Events;

public interface TicketService {

    TicketResponseDto createTicket(String title);

    TicketResponseDto sendEvent(Long id, Events event);
}
