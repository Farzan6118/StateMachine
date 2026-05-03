//package com.example.statemachine.config;
//
//import com.example.statemachine.domain.enums.Events;
//import com.example.statemachine.domain.enums.States;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.statemachine.config.EnableStateMachineFactory;
//import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
//import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
//import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
//import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
//import org.springframework.statemachine.listener.StateMachineListener;
//import org.springframework.statemachine.listener.StateMachineListenerAdapter;
//import org.springframework.statemachine.state.State;
//
//import java.util.EnumSet;
//import java.util.Set;
//
//@Configuration
//@EnableStateMachineFactory
//public class StateMachineConfigWithEnum extends EnumStateMachineConfigurerAdapter<States, Events> {
//    @Override
//    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
//            throws Exception {
//        config
//                .withConfiguration()
//                .autoStartup(true)
//                .listener(listener());
//    }
//
//    @Override
//    public void configure(StateMachineStateConfigurer<States, Events> states)
//            throws Exception {
//        states
//                .withStates()
//                .initial(States.NEW)
//                .states(EnumSet.allOf(States.class));
//    }
//
//    @Override
//    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
//            throws Exception {
//        transitions
//                .withExternal()
//                .source(States.NEW).target(States.ASSIGNED).event(Events.START)
//                .and()
//                .withExternal()
//                .source(States.ASSIGNED).target(States.IN_PROGRESS).event(Events.ASSIGN)
//                .and()
//                .withExternal()
//                .source(States.IN_PROGRESS).target(States.DONE).event(Events.FINISH)
//                .and()
//                .withExternal()
//                .source(States.DONE).target(States.CLOSED).event(Events.CLOSE);
//    }
//
//    @Bean
//    public StateMachineListener<States, Events> listener() {
//        return new StateMachineListenerAdapter<States, Events>() {
//            @Override
//            public void stateChanged(State<States, Events> from, State<States, Events> to) {
//                System.out.println("State change to " + to.getId());
//            }
//        };
//    }
//}