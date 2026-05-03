package com.example.statemachine.service;

import com.example.statemachine.controller.dto.response.EnumResponseDto;

import java.util.List;

public interface EnumService {

    List<EnumResponseDto> getAllStates();

    List<EnumResponseDto> getAllEvents();
}
