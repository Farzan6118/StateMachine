package com.example.statemachine.controller.dto.response;

import com.example.statemachine.domain.enums.States;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDto {

    private String title;
    private States state;
}
