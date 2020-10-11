package seedu.address.model.calendar;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a bidderId in the address book to be used in calendar.
 * Guarantees: immutable; is valid as declared in {@link #isValidBidderId(String)}
 */
public class CalendarBidderId {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String bidderId;

    /**
     * Constructs a {@code CalendarBidderId}.
     *
     * @param name A valid name.
     */
    public CalendarBidderId(String name) {
        requireNonNull(name);
        checkArgument(isValidBidderId(name), MESSAGE_CONSTRAINTS);
        bidderId = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidBidderId(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return bidderId;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalendarBidderId // instanceof handles nulls
                && bidderId.equals(((CalendarBidderId) other).bidderId)); // state check
    }

    @Override
    public int hashCode() {
        return bidderId.hashCode();
    }
}
