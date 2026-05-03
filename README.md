### 🎫 Spring State Machine – Ticket Workflow

This project demonstrates how to implement a state machine using Spring Boot and Spring State Machine to manage a ticket lifecycle.

### 🧠 Overview

The system models a simple ticket workflow, where each ticket moves through defined states based on events.

### States
NEW → ASSIGNED → IN_PROGRESS → DONE → CLOSED

### Events
- ASSIGN
- START
- FINISH
- CLOSE

### ⚙️ Tech Stack
- Java 21
- Spring Boot
- Spring State Machine
- Spring Data JPA
- Maven
### 🔄 Workflow
```
  NEW --(START)--> ASSIGNED
  ASSIGNED --(START)--> IN_PROGRESS
  IN_PROGRESS --(FINISH)--> DONE
  DONE --(CLOSE)--> CLOSED
```

### 🏗️ Key Concepts
State Machine Configuration using EnumStateMachineConfigurerAdapter
Entity Persistence for Ticket
State Change Tracking via TicketStateHistory
Listener to capture and store transitions

### 🧩 Example Configuration

```
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates()
              .initial(States.NEW)
              .states(Set.of(States.values()));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
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
```

### 🗄️ Persistence
- Ticket → holds current state
- TicketStateHistory → logs all transitions

A listener captures every state change and stores it in the database.

### 🎯 Why This Matters

Using a state machine:

- Simplifies complex workflows
- Removes messy conditional logic
- Improves maintainability and traceability
### 👤 Author

Farzan Saketi
Senior Java Backend Developer
