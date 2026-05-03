package com.example.statemachine.controller.restController;

import com.example.statemachine.controller.dto.response.TicketResponseDto;
import com.example.statemachine.domain.enums.Events;
import com.example.statemachine.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService service;

    @PostMapping
    public TicketResponseDto createTicket(@RequestParam String title) {
        return service.createTicket(title);
    }

    @PostMapping("/{id}/event")
    public TicketResponseDto sendEvent(@PathVariable Long id, @RequestParam Events event) {
        return service.sendEvent(id, event);
    }
}