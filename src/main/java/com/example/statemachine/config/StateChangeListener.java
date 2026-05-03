package com.example.statemachine.config;

import com.example.statemachine.domain.entity.TicketStateHistory;
import com.example.statemachine.domain.enums.Events;
import com.example.statemachine.domain.enums.States;
import com.example.statemachine.repository.TicketStateHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StateChangeListener extends StateMachineListenerAdapter<States, Events> {

    private final TicketStateHistoryRepository historyRepository;

    @Override
    public void stateChanged(State<States, Events> from, State<States, Events> to) {

        if (from == null || to == null) return;

        TicketStateHistory ticketStateHistory = new TicketStateHistory();
        ticketStateHistory.setFromState(from.getId());
        ticketStateHistory.setToState(from.getId());

        historyRepository.save(ticketStateHistory);
    }
}