package seedu.address.model.meeting.meeting;

import seedu.address.model.util.Time;

/**
 * Represents a Meeting's to in the meeting book.
 */
public class To extends Time {

    public static final String MESSAGE_CONSTRAINTS = String.format(CONSTRAINTS, "To");

    /**
     * Constructs a {@code To}.
     *
     * @param to A valid To.
     */
    public To(String to) {
        super(to);
    }

    /**
     * Returns true if a given string is a valid To.
     */
    public static boolean isValidTo(String test) {
        return isValidTime(test);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof To
                && value.equals(((To) other).value));
    }

}
