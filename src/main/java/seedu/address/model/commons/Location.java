package seedu.address.model.commons;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_STRING_EXCEED_CHARACTER_LIMIT;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.StringUtil.isWithinCharacterLimit;

public class Location {
    /**
     * Represents Activity's or Accommodation's location in Wanderlust.
     * Guarantees: immutable; is valid as declared in {@link #isValidLocation(String)}
     */
    public static final int CHARACTER_LIMIT = 200;

    public static final String MESSAGE_CONSTRAINTS = "Location can take any values, and it should not be blank";
    public static final String MESSAGE_LOCATION_EXCEEDS_CHARACTER_LIMIT =
            String.format(MESSAGE_STRING_EXCEED_CHARACTER_LIMIT, "Location", CHARACTER_LIMIT);

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "(\\p{Graph}+\\p{Space}?)+";

    private final String value;

    /**
     * Constructs an {@code Location}.
     *
     * @param location A valid location.
     */
    public Location(String location) {
        requireNonNull(location);
        checkArgument(isWithinCharacterLimit(location, CHARACTER_LIMIT), MESSAGE_LOCATION_EXCEEDS_CHARACTER_LIMIT);
        checkArgument(isValidLocation(location), MESSAGE_CONSTRAINTS);
        value = location;
    }

    /**
     * Returns true if a given string is a valid location.
     */
    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Location // instanceof handles nulls
                && value.equals(((Location) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
