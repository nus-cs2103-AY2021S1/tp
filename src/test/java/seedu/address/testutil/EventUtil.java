package seedu.address.testutil;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventUtil {

    public static final EventName VALID_NAME = EventUtil.makeEventName("Test");
    public static final EventTime VALID_DATE = EventUtil.makeEventTime("1-2-2020 1200");
    public static final Event VALID_EVENT = EventUtil.makeEvent(VALID_NAME, VALID_DATE);

    public static EventName makeEventName(String name) {
        return new EventName(name);
    }

    public static EventTime makeEventTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-uuuu HHmm");
        return new EventTime(LocalDateTime.parse(time, formatter));
    }

    public static EventTime makeEventTime(String time1, String time2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-uuuu HHmm");
        return new EventTime(LocalDateTime.parse(time1, formatter), LocalDateTime.parse(time2, formatter));
    }

    public static LocalDateTime makeLocalDateTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-uuuu HHmm");
        return LocalDateTime.parse(time, formatter);
    }

    public static Event makeEvent(EventName name, EventTime time) {
        return new Event(name, time);
    }
}
