package com.example.statemachine.service.impl;

import com.example.statemachine.controller.dto.response.TicketResponseDto;
import com.example.statemachine.domain.entity.Ticket;
import com.example.statemachine.domain.entity.TicketStateHistory;
import com.example.statemachine.domain.enums.Events;
import com.example.statemachine.domain.enums.States;
import com.example.statemachine.repository.TicketRepository;
import com.example.statemachine.repository.TicketStateHistoryRepository;
import com.example.statemachine.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository repository;
    private final TicketStateHistoryRepository historyRepository;
    private final StateMachineFactory<States, Events> factory;

    @Override
    public TicketResponseDto createTicket(String title) {
        Ticket ticket = new Ticket();
        ticket.setState(States.NEW);
        ticket.setTitle(title);
        Ticket saved = repository.save(ticket);
        return TicketResponseDto.builder()
                .state(saved.getState())
                .title(saved.getTitle()).build();
    }

    @Override
    public TicketResponseDto sendEvent(Long id, Events event) {
        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket not found"));

        StateMachine<States, Events> sm = factory.getStateMachine();

        // set current state from DB
        sm.stop();
        sm.getStateMachineAccessor()
                .doWithAllRegions(access ->
                        access.resetStateMachine(
                                new DefaultStateMachineContext<>(ticket.getState(), null, null, null)
                        )
                );
        sm.start();

        States from = sm.getState().getId();

        boolean accepted = sm.sendEvent(
                MessageBuilder.withPayload(event)
                        .setHeader("ticketId", id)
                        .setHeader("event", event)
                        .build()
        );

        if (!accepted) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid transition");
        }

        States to = sm.getState().getId();
        ticket.setState(to);

        Ticket saved = repository.save(ticket);

        TicketStateHistory ticketStateHistory = new TicketStateHistory();
        ticketStateHistory.setFromState(from);
        ticketStateHistory.setToState(to);
        ticketStateHistory.setEvent(event);

        historyRepository.save(ticketStateHistory);

        return TicketResponseDto.builder()
                .state(saved.getState())
                .title(saved.getTitle()).build();
    }
}