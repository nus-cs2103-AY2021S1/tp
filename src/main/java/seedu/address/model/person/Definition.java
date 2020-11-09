package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Flashcard's definition in the flashcard book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDefinition(String)} (String)}
 */
public class Definition {

    public static final String MESSAGE_CONSTRAINTS = "Definitions can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code Definition}.
     *
     * @param definition A valid definition.
     */
    public Definition(String definition) {
        requireNonNull(definition);
        checkArgument(isValidDefinition(definition), MESSAGE_CONSTRAINTS);
        value = definition;
    }

    /**
     * Constructs an empty {@code Definition}
     */
    public Definition() {
        value = "";
    }

    /**
     * Returns true if a given string is a valid definition.
     */
    public static boolean isValidDefinition(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Definition // instanceof handles nulls
                && value.equals(((Definition) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
