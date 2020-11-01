package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class ModuleName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank.\n"
                + "Names should not be more than 52 characters in length.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private static final Integer MAX_MODULE_NAME_LENGTH = 52;

    public final String moduleName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public ModuleName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        moduleName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX)
            && test.length() <= MAX_MODULE_NAME_LENGTH;
    }

    @Override
    public String toString() {
        return moduleName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleName // instanceof handles nulls
                && moduleName.equals(((ModuleName) other).moduleName)); // state check
    }

    @Override
    public int hashCode() {
        return moduleName.hashCode();
    }

}
