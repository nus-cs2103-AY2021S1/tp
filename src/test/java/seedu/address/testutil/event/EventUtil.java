package seedu.address.testutil.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;

public class EventUtil {

    public static final EventName VALID_NAME = EventUtil.makeEventName("Test");
    public static final String VALID_NAME_STRING = "Test";
    public static final EventTime VALID_DATE = EventUtil.makeEventTime("1-2-2020 1200");
    public static final String VALID_DATE_STRING = "1-2-2020 1200";
    public static final Event VALID_EVENT = EventUtil.makeEvent(VALID_NAME, VALID_DATE);
    public static final Event DEFAULT_EVENT = new Event(new EventName("Homework"), new EventTime());

    /**
     * Creates an EventName based on the given String input.
     * @param name String of name.
     * @return EventName the name of the Event.
     */
    public static EventName makeEventName(String name) {
        return new EventName(name);
    }

    /**
     * Creates an EventTime based on the given string input.
     * @param time String of the time and date.
     * @return EventTime the date and time of the Event.
     */
    public static EventTime makeEventTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-uuuu HHmm");
        return new EventTime(LocalDateTime.parse(time, formatter));
    }

    /**
     * Creates an EventTime based on the given string input.
     * @param time1 String start time.
     * @param time2 String end time.
     * @return EvenTime the start and end time.
     */
    public static EventTime makeEventTime(String time1, String time2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-uuuu HHmm");
        return new EventTime(LocalDateTime.parse(time1, formatter), LocalDateTime.parse(time2, formatter));
    }

    /**
     * Creates a LocalDateTime based on the given string input.
     * @param time time in string.
     * @return LocalDateTime of the given input.
     */
    public static LocalDateTime makeLocalDateTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-uuuu HHmm");
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * Creates an Event based on the given EventName and EventTime.
     * @param name EventName.
     * @param time EventTime.
     * @return Event with the name and time.
     */
    public static Event makeEvent(EventName name, EventTime time) {
        return new Event(name, time);
    }
}
