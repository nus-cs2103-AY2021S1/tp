package seedu.address.model.attraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Attraction's rating in TrackPad.
 * Guarantees: immutable; is valid as declared in {@link #isValidRating(String)} (String)}
 */
public class Rating {

    public static final String MESSAGE_CONSTRAINTS = "Rating should only contain a number "
            + "between 0.0 to 5.0(inclusive), to 1 decimal place.";
    public static final String VALIDATION_REGEX = "^([0-4]\\.[0-9]|5\\.0)$|^$";

    public final String value;

    /**
     * Constructs a {@code Rating}.
     *
     * @param rating A valid rating.
     */
    public Rating(String rating) {
        requireNonNull(rating);
        checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);
        value = rating;
    }

    /**
     * Constructs a {@code Rating} without value.
     */
    public Rating() {
        value = "";
    }

    /**
     * Returns if a given string is a valid rating.
     */
    public static boolean isValidRating(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        if (value.isEmpty()) {
            return "";
        } else {
            return value + "/5.0";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rating // instanceof handles nulls
                && value.equals(((Rating) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
