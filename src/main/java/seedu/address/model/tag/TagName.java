package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTagName(String)}
 */
public class TagName {

    public static final String MESSAGE_CONSTRAINTS =
            "Tag names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String tagName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid tag name.
     */
    public TagName(String name) {
        requireNonNull(name);
        checkArgument(isValidTagName(name), MESSAGE_CONSTRAINTS);
        tagName = name;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return tagName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagName // instanceof handles nulls
                && tagName.equals(((TagName) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

}
