package seedu.jarvis.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.jarvis.model.task.Deadline;

public class DeadlineBuilder {

    public static final String DEFAULT_EVENT_DESCRIPTION = "CS2101 UG submission";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_EVENT_DATE_TIME = "2020-11-26 21:30";

    private String description;
    private LocalDateTime dateTime;

    /**
     * Sets both description and dateTime of the to be created Deadline to be the default values defined in this class.
     */
    public DeadlineBuilder() {
        description = DEFAULT_EVENT_DESCRIPTION;
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
        dateTime = LocalDateTime.parse(DEFAULT_EVENT_DATE_TIME, dateTimeFormat);
    }

    /**
     * Sets the description of the Deadline we are building
     * @param newDescription
     * @return
     */
    public DeadlineBuilder withDescription(String newDescription) {
        description = newDescription;
        return this;
    }

    /**
     * Sets the date time of the Deadline we are building
     * @param newDateTime
     * @return
     */
    public DeadlineBuilder withDateTime(String newDateTime) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
        dateTime = LocalDateTime.parse(newDateTime, dateTimeFormat);
        return this;
    }

    public Deadline build() {
        return new Deadline(description, dateTime);
    }

    public Deadline buildJson(String taskId) {
        return new Deadline(taskId, description, dateTime.toString());
    }

}
