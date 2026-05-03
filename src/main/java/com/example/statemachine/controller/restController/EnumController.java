package com.example.statemachine.controller.restController;

import com.example.statemachine.controller.dto.response.EnumResponseDto;
import com.example.statemachine.service.EnumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enum")
@RequiredArgsConstructor
public class EnumController {

    private final EnumService enumService;

    @GetMapping("/states")
    public ResponseEntity<List<EnumResponseDto>> getAllStates() {
        return ResponseEntity.ok(enumService.getAllStates());
    }

    @GetMapping("/events")
    public ResponseEntity<List<EnumResponseDto>> getAllEvents() {
        return ResponseEntity.ok(enumService.getAllEvents());
    }

}
