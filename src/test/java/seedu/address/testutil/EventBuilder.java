package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;
import seedu.address.model.task.event.EndDateTime;
import seedu.address.model.task.event.Event;
import seedu.address.model.task.event.StartDateTime;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class EventBuilder {

    public static final String DEFAULT_TITLE = "School work";
    public static final String DEFAULT_START_DATE_TIME = "01-01-2020 12:00";
    public static final String DEFAULT_END_DATE_TIME = "01-01-2020 12:00";
    public static final String DEFAULT_DESCRIPTION = "6 midterms next week.";
    public static final boolean DEFAULT_ISLESSON = false;

    private Title title;
    private StartDateTime startDateTime;
    private EndDateTime endDateTime;
    private Description description;
    private Set<Tag> tags;
    private boolean isLesson;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public EventBuilder() {
        title = new Title(DEFAULT_TITLE);
        startDateTime = new StartDateTime(DEFAULT_START_DATE_TIME);
        endDateTime = new EndDateTime(DEFAULT_END_DATE_TIME);
        description = new Description(DEFAULT_DESCRIPTION);
        isLesson = DEFAULT_ISLESSON;
        tags = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        title = eventToCopy.getTitle();
        startDateTime = eventToCopy.getStartDateTime();
        endDateTime = eventToCopy.getEndDateTime();
        description = eventToCopy.getDescription();
        isLesson = eventToCopy.isLesson();
        tags = new HashSet<>();
    }

    /**
     * Sets the {@code Title} of the {@code Event} that we are building.
     */
    public EventBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public EventBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code Task} that we are building.
     */
    public EventBuilder withStartDateTime(String startDateTime) {
        this.startDateTime = new StartDateTime(startDateTime);
        return this;
    }

    /**
     * Sets the {@code dateTime} of the {@code Task} that we are building.
     */
    public EventBuilder withEndDateTime(String endDateTime) {
        this.endDateTime = new EndDateTime(endDateTime);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public EventBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building to be default description.
     * Simulates the situation that the task is created without a description field.
     */
    public EventBuilder withDefaultDescription() {
        this.description = Description.defaultDescription();
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Deadline} that we are building to be incomplete status.
     * Simulates the situation that the deadline is first been created.
     */
    public EventBuilder withNotALesson() {
        this.isLesson = false;
        return this;
    }

    public Event build() {
        return new Event(title, startDateTime, endDateTime, description, tags, isLesson);
    }
}
