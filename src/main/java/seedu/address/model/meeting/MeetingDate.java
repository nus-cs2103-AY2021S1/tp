package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Meeting's MeetingDate in the Meeting book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class MeetingDate {

    public static final String MESSAGE_CONSTRAINTS = "Meeting Date should only contain numbers,"
            + " and it should not be blank";
    public static final String MESSAGE_CONSTRAINTS_PAST_DATE = "Meeting Date should be a date from today and should"
            + "not be of a past date";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String date;

    /**
     * Constructs an {@code MeetingDate}.
     *
     * @param date A valid MeetingDate.
     */
    public MeetingDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = date;
    }

    /**
     * Returns true if a given string is a valid meetingDate.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.date;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingDate // instanceof handles nulls
                && this.date.equals(((MeetingDate) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return this.date.hashCode();
    }

}
