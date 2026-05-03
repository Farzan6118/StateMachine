package com.example.statemachine.config;

import com.example.statemachine.domain.enums.Events;
import com.example.statemachine.domain.enums.States;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.Set;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.NEW)
                .states(Set.of(States.NEW, States.ASSIGNED, States.IN_PROGRESS, States.DONE, States.CLOSED));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {

        transitions
                .withExternal().source(States.NEW).target(States.ASSIGNED).event(Events.START)
                .and()
                .withExternal().source(States.ASSIGNED).target(States.IN_PROGRESS).event(Events.START)
                .and()
                .withExternal().source(States.IN_PROGRESS).target(States.DONE).event(Events.FINISH)
                .and()
                .withExternal().source(States.DONE).target(States.CLOSED).event(Events.CLOSE);
    }

}