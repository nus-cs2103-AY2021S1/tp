package seedu.address.model.meeting.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Meeting's from in the meeting book.
 */
public class From {
    public static final String MESSAGE_CONSTRAINTS =
            "froms should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * Format should be MM/DD/YYYY or MM/DD/YY HH:mm
     * Single digit month, day, and minute can start with a leading zero.
     */

    //Solution below adapted from https://stackoverflow.com/a/51231
    public static final String VALIDATION_REGEX = "[0-9]{2}/[0-9]{2}/[0-9]{4} [0-23]{2}:[0-59]{2}";

    public final String value;

    /**
     * Constructs a {@code from}.
     *
     * @param from A valid from.
     */
    public From(String from) {
        requireNonNull(from);
        checkArgument(isValidFrom(from), MESSAGE_CONSTRAINTS);
        value = from;
    }

    /**
     * Returns true if a given string is a valid from.
     */
    public static boolean isValidFrom(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof From
                && value.equals(((From) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
