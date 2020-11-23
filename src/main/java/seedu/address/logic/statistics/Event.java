package seedu.address.logic.statistics;

import java.time.LocalDateTime;

public class Event {

    private EventType eventType;
    private LocalDateTime localDateTime;

    /**
     * @param eventType
     * @param localDateTime
     */
    public Event(EventType eventType, LocalDateTime localDateTime) {
        this.eventType = eventType;
        this.localDateTime = localDateTime;
    }

    /**
     * @param eventType
     */
    public Event(EventType eventType) {
        this.eventType = eventType;
        this.localDateTime = LocalDateTime.now();
    }

    public EventType getEventType() {
        return eventType;
    }

    @Override
    public String toString() {
        return "Event{"
                + "eventType=" + eventType
                + ", localDateTime=" + localDateTime
                + '}';
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
