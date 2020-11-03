package seedu.address.testutil;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventUtil {

    public static EventName makeEventName(String name) {
        return new EventName(name);
    }

    public static EventTime makeEventTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-uuuu HHmm");
        return new EventTime(LocalDateTime.parse(time, formatter));
    }

    public static Event makeEvent(EventName name, EventTime time) {
        return new Event(name, time);
    }
}
