package seedu.jarvis.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.jarvis.model.task.Event;

public class EventBuilder {

    public static final String DEFAULT_EVENT_DESCRIPTION = "CS2103T tp submission";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_EVENT_DATE_TIME = "2020-12-26 23:59";

    private String description;
    private LocalDateTime dateTime;

    /**
     * Sets both description and dateTime of the to be created Event to be the default values defined in this class.
     */
    public EventBuilder() {
        description = DEFAULT_EVENT_DESCRIPTION;
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
        dateTime = LocalDateTime.parse(DEFAULT_EVENT_DATE_TIME, dateTimeFormat);
    }

    /**
     * Sets the description of the Event we are building
     * @param newDescription
     * @return
     */
    public EventBuilder withDescription(String newDescription) {
        description = newDescription;
        return this;
    }

    /**
     * Sets the date time of the Event we are building
     * @return
     */
    public EventBuilder withDateTime(String newDateTime) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
        dateTime = LocalDateTime.parse(newDateTime, dateTimeFormat);
        return this;
    }

    public Event build() {
        return new Event(description, dateTime);
    }

    public Event buildJson(String taskId) {
        return new Event(taskId, description, dateTime.toString());
    }

}
