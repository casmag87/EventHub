package state;

import java.util.EnumMap;
import java.util.Map;

public class StateMachine {

    private final Map<State, Map<Event, State>> transitions;

    public StateMachine(){
        this.transitions = new EnumMap<>(State.class);
        initializeTransitions();

    }

    private void initializeTransitions(){

        transitions.put(State.NEW, new EnumMap<>(Event.class));
        transitions.get(State.NEW).put(Event.START_PROGRESS,State.IN_PROGRESS);

        transitions.put(State.IN_PROGRESS,new EnumMap<>(Event.class));
        transitions.get(State.IN_PROGRESS).put(Event.PUT_ON_HOLD,State.ON_HOLD);
        transitions.get(State.IN_PROGRESS).put(Event.ESCALATE, State.ESCALATED);
        transitions.get(State.IN_PROGRESS).put(Event.RESOLVE, State.RESOLVED);

        transitions.put(State.ON_HOLD, new EnumMap<>(Event.class));
        transitions.get(State.ON_HOLD).put(Event.RESUME, State.IN_PROGRESS);
        transitions.get(State.ON_HOLD).put(Event.ESCALATE, State.ESCALATED);

        transitions.put(State.ESCALATED, new EnumMap<>(Event.class));
        transitions.get(State.ESCALATED).put(Event.RESOLVE,State.RESOLVED);
        transitions.get(State.ESCALATED).put(Event.DE_ESCALATE,State.IN_PROGRESS);

        transitions.put(State.RESOLVED, new EnumMap<>(Event.class));
        transitions.get(State.RESOLVED).put(Event.CLOSE, State.CLOSED);
        transitions.get(State.RESOLVED).put(Event.RESUME, State.IN_PROGRESS);

        transitions.put(State.CLOSED, new EnumMap<>(Event.class));
        transitions.get(State.CLOSED).put(Event.REOPEN, State.IN_PROGRESS);
    }



    public State transition(State currentState, Event event) {
        Map<Event, State> stateTransitions = transitions.get(currentState);

        if (stateTransitions == null) {
            throw new IllegalStateException("No transitions defined for state: " + currentState);
        }

        State nextState = stateTransitions.get(event);

        if (nextState == null) {
            throw new IllegalStateException(
                    "Invalid event: " + event + " for state: " + currentState
            );
        }

        return nextState;
    }

    public boolean canTransition(State currentState, Event event) {
        Map<Event, State> stateTransitions = transitions.get(currentState);
        return stateTransitions != null && stateTransitions.containsKey(event);
    }

}
