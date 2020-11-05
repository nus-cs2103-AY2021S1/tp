package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEventName(String)}
 */
public class EventName {

    public static final String MESSAGE_CONSTRAINTS =
            "Event names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the event name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String eventName;

    /**
     * Constructs an {@code EventName}.
     *
     * @param eventName A valid name.
     */
    public EventName(String eventName) {
        requireNonNull(eventName);
        checkArgument(isValidEventName(eventName), MESSAGE_CONSTRAINTS);
        this.eventName = eventName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidEventName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return eventName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventName // instanceof handles nulls
                && eventName.equalsIgnoreCase(((EventName) other).eventName)); // state check
    }

    @Override
    public int hashCode() {
        return eventName.hashCode();
    }

}

