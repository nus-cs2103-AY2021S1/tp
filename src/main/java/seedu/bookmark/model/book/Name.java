package seedu.bookmark.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.util.AppUtil.checkArgument;

/**
 * Represents a Book's name in the library.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final int MAX_NAME_LENGTH = 120;

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank. \n"
            + String.format("Maximum of %d characters allowed, including spaces.", MAX_NAME_LENGTH);

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]"
            + "{1," + MAX_NAME_LENGTH + "}$";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.toLowerCase().equals(((Name) other).fullName.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
