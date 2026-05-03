package com.example.statemachine.service.impl;

import com.example.statemachine.controller.dto.response.EnumResponseDto;
import com.example.statemachine.domain.enums.Events;
import com.example.statemachine.domain.enums.States;
import com.example.statemachine.service.EnumService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EnumServiceImpl implements EnumService {

    @Override
    public List<EnumResponseDto> getAllStates() {
        return Arrays.stream(States.values())
                .map(this::map)
                .toList();
    }

    @Override
    public List<EnumResponseDto> getAllEvents() {
        return Arrays.stream(Events.values())
                .map(this::map)
                .toList();
    }

    private <T> EnumResponseDto map(T e) {
        return EnumResponseDto.builder()
                .name(e.toString())
                .build();
    }

}
