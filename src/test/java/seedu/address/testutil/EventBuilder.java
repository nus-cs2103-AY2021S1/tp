package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {
    public static final String DEFAULT_NAME = "Hall Event X";
    public static final String DEFAULT_DATE = "27/10/2020 15:00";
    public static final String DEFAULT_LOCATION = "Dining Hall";
    public static final String DEFAULT_DESCRIPTION = "The Xth event of the Hall";
    public static final Set<Person> DEFAULT_ATTENDEES = new HashSet<>();

    private EventName eventName;
    private EventDate eventDate;
    private Location location;
    private Description description;
    private Set<Person> attendees;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        eventName = new EventName(DEFAULT_NAME);
        eventDate = new EventDate(DEFAULT_DATE);
        location = new Location(DEFAULT_LOCATION);
        description = new Description(DEFAULT_DESCRIPTION);
        attendees = Set.copyOf(DEFAULT_ATTENDEES);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}
     */
    public EventBuilder(Event event) {
        eventName = event.getName();
        eventDate = event.getEventDate();
        location = event.getLocation();
        description = event.getDescription();
        attendees = Set.copyOf(event.getAttendeesList());

    }

    /**
     * Sets the {@code eventName} of the {@code Event} that we are building.
     */
    public EventBuilder withEventName(String name) {
        this.eventName = new EventName(name);
        return this;
    }

    /**
     * Sets the {@code eventDate} of the {@code Event} that we are building.
     */
    public EventBuilder withDate(String date) {
        this.eventDate = new EventDate(date);
        return this;
    }

    /**
     * Sets the {@code location} of the {@code Event} that we are building.
     */
    public EventBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code description} of the {@code Event} that we are building.
     */
    public EventBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Set the {@code attendees} of the {@code Event} that we are building.
     */
    public EventBuilder withAttendees(Set<Person> attendees) {
        this.attendees = Set.copyOf(attendees);
        return this;
    }

    /**
     * Converts the current {@code EventBuilder} to an actual {@code Event}.
     */
    public Event build() {
        return new Event(eventName, eventDate, location, description, attendees);
    }
}
