package seedu.address.model.attraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Attraction's Opening hours in TrackPad.
 * Guarantees: immutable; is valid as declared in {@link #isValidOpeningHours(String)} (String)}
 */
public class OpeningHours {

    public static final String MESSAGE_CONSTRAINTS = "Opening Hours should be of the format "
            + "opening time (in 24 hour format) - closing time (in 24 hour format).";
    private static final String VALIDATION_REGEX =
            "([01][0-9]|2[0-3])[0-5][0-9]-([01][0-9]|2[0-3])[0-5][0-9]|^$";

    public final String value;

    /**
     * Constructs an {@code OpeningHours}.
     *
     * @param openingHours A valid opening hour.
     */
    public OpeningHours(String openingHours) {
        requireNonNull(openingHours);
        checkArgument(isValidOpeningHours(openingHours), MESSAGE_CONSTRAINTS);
        value = openingHours;
    }

    /**
     * Constructs an {@code OpeningHours} without value.
     */
    public OpeningHours() {
        value = "";
    }

    /**
     * Returns if a given string is a valid opening hour.
     */
    public static boolean isValidOpeningHours(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OpeningHours // instanceof handles nulls
                && value.equals(((OpeningHours) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
