package seedu.address.model.commons;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_STRING_EXCEED_CHARACTER_LIMIT;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.StringUtil.isWithinCharacterLimit;

/**
 * Represents activity's or accommodation's or friend's or travel plan's name in Wanderlust.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final int CHARACTER_LIMIT = 80;

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters, punctuations and spaces, and it should not be blank.";
    public static final String MESSAGE_NAME_EXCEEDS_CHARACTER_LIMIT =
            String.format(MESSAGE_STRING_EXCEED_CHARACTER_LIMIT, "Name", CHARACTER_LIMIT);

    /*
     * The first character of the Name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "(\\p{Alnum}(\\p{Alnum}|'|,)*\\p{Space}?)+";

    private final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isWithinCharacterLimit(name, CHARACTER_LIMIT), MESSAGE_NAME_EXCEEDS_CHARACTER_LIMIT);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.value = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
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
                || (other instanceof Name // instanceof handles nulls
                && value.equals(((Name) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
