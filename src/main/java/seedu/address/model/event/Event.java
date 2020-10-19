package seedu.address.model.event;

public class Event {

    private final EventName name;
    private final EventTime time;

    public Event(EventName name, EventTime time) {
        assert(name != null);
        this.name = name;
        this.time = time;
    }
}
