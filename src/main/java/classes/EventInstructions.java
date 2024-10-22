package classes;

import abstracted.Enum.StatefulObjectTypes;

public class EventInstructions {
    StatefulObjectTypes nextEventType;
    String nextEventTarget;

    public EventInstructions(StatefulObjectTypes nextEventType, String nextEventTarget) {
        this.nextEventType = nextEventType;
        this.nextEventTarget = nextEventTarget;
    }

    public StatefulObjectTypes getNextEventType() {
        return nextEventType;
    }

    public String getNextEventTarget() {
        return nextEventTarget;
    }
}


