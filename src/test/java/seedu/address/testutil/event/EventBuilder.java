package seedu.address.testutil.event;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;


/**
 * A utility class to help with building Module objects.
 */
public class EventBuilder {
    public static final String DEFAULT_EVENTNAME = "CS2020 Assignment";
    public static final String DEFAULT_DATE = "1-2-2020 1200";

    private EventName eventName;
    private EventTime eventTime;
    private Set<Tag> tags;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        eventName = EventUtil.makeEventName(DEFAULT_EVENTNAME);
        eventTime = EventUtil.makeEventTime(DEFAULT_DATE);
        tags = new HashSet<Tag>();
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        eventName = eventToCopy.getName();
        eventTime = eventToCopy.getTime();
        tags = new HashSet<>(eventToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.eventName = new EventName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Event} that we are building.
     */
    public EventBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code ZoomLinks} of the {@code Module} that we are building.
     */
    public EventBuilder withDate(String date) {
        this.eventTime = EventUtil.makeEventTime(date);
        return this;
    }

    /**
     * Builds the Event.
     *
     * @return an Event
     */
    public Event build() {
        return new Event(this.eventName, this.eventTime, this.tags);
    }
}
