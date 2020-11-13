package seedu.address.model.meeting.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import seedu.address.model.event.TimeEvent;
import seedu.address.model.util.Contacts;
import seedu.address.model.util.Description;
import seedu.address.model.util.Title;

/**
 * Represents a Meeting in the meeting book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Meeting implements TimeEvent {

    public static final String INCORRECT_FROM_AND_TO_ORDER = "From time should be earlier than To.";

    private final Title title;
    private final Description description;
    private final From from;
    private final To to;
    private final Contacts contacts;
    private final Location location;

    /**
     * Every field must be present and not null.
     */
    public Meeting(Title title, Description description, From from, To to,
                   Contacts contacts, Location location) throws IllegalArgumentException {

        requireAllNonNull(title, description, from, to, contacts, location);

        if (!isValidFromAndTo(from, to)) {
            throw new IllegalArgumentException(INCORRECT_FROM_AND_TO_ORDER);
        }

        this.title = title;
        this.description = description;
        this.from = from;
        this.to = to;
        this.contacts = contacts;
        this.location = location;
    }

    /**
     * Returns true if From is earlier than To chronologically.
     */
    public static boolean isValidFromAndTo (From from, To to) {
        LocalTime dateFrom = from.getLocalDateTime().toLocalTime();
        LocalTime dateTo = to.getLocalTime();

        return dateFrom.compareTo(dateTo) < 0;
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public From getFrom() {
        return from;
    }

    @Override
    public LocalDateTime getIndicatorTime() {
        return from.getLocalDateTime();
    }

    public To getTo() {
        return to;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Returns true if both meetings have the same identity.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getTitle().equals(getTitle())
                && otherMeeting.getFrom().equals(getFrom())
                && otherMeeting.getTo().equals(getTo());
    }

    /**
     * Returns true if both meetings have the same identity.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.getTitle().equals(getTitle())
                && otherMeeting.getDescription().equals(getDescription())
                && otherMeeting.getFrom().equals(getFrom())
                && otherMeeting.getTo().equals(getTo())
                && otherMeeting.getContacts().equals(getContacts())
                && otherMeeting.getLocation().equals(getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, from, to, contacts, location);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Title: ")
                .append(getTitle())
                .append(" From: ")
                .append(getFrom())
                .append(" To: ")
                .append(getTo())
                .append(" Contact(s): ")
                .append(getContacts())
                .append(" Location: ")
                .append(getLocation())
                .append(" Description: ")
                .append(getDescription());
        return builder.toString();
    }

}
