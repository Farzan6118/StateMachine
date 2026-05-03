package com.example.statemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StateMachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(StateMachineApplication.class, args);
    }

}
