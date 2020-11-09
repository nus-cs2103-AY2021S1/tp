package seedu.address.testutil;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Event;
import seedu.address.model.task.MeetingLink;
import seedu.address.model.task.Recurrence;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building To-do objects.
 */
public class EventBuilder {
    /**
     * The format of inputted dates that the class can accept.
     */
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    /**
     * The format of outputted dates by the class.
     */
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    private static final String DEFAULT_DESC = "meeting";
    private static final String DEFAULT_STARTDATETIME = "12-12-2020 1000";
    private static final String DEFAULT_ENDDATETIME = "12-12-2020 1130";

    private String description;
    private String startDateTime;
    private String endDateTime;
    private Recurrence recurrence;
    private MeetingLink link;
    private Set<Tag> tags;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        description = DEFAULT_DESC;
        startDateTime = DEFAULT_STARTDATETIME;
        endDateTime = DEFAULT_ENDDATETIME;
        tags = new HashSet<>();
    }

    /**
     * Initializes the EventBuilder with the data of {@code EventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        description = eventToCopy.getDescription();
        startDateTime = eventToCopy.getStartDateTime();
        endDateTime = eventToCopy.getEndDateTime();
        recurrence = eventToCopy.getRecurrence();
        link = eventToCopy.getMeetingLink();
        tags = new HashSet<>(eventToCopy.getTags());
    }

    /**
     * Sets the {@code Description} of the {@code To-do} that we are building.
     */
    public EventBuilder withDescription(String desc) {
        this.description = desc;
        return this;
    }

    /**
     * Sets the {@code startDateTime} of the {@code Event} that we are building.
     */
    public EventBuilder withStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
        return this;
    }

    /**
     * Sets the {@code endDateTime} of the {@code Event} that we are building.
     */
    public EventBuilder withEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
        return this;
    }

    /**
     * Sets the {@code Recurrence} of the {@code Event} that we are building.
     */
    public EventBuilder withRecurrence(String recurrenceInput) {
        String[] recurrenceSplit = recurrenceInput.split(" ");
        Integer recurrenceValue = Integer.parseInt(recurrenceSplit[0]);
        String recurrenceTimePeriod = recurrenceSplit[1];
        this.recurrence = new Recurrence(recurrenceValue, recurrenceTimePeriod);
        return this;
    }

    /**
     * Sets the {@code MeetingLink} of the {@code Event} that we are building.
     */
    public EventBuilder withLink(MeetingLink link) {
        this.link = link;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Event} that we are building.
     */
    public EventBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }


    public Event build() {
        return new Event(description, startDateTime, endDateTime, recurrence, link, tags);
    }

}
