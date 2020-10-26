package seedu.zookeep.model.animal;

import static java.util.Objects.requireNonNull;
import static seedu.zookeep.commons.util.AppUtil.checkArgument;

/**
 * Represents an animal's ID number in the animal list.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {


    public static final String MESSAGE_CONSTRAINTS =
            "ID numbers should only contain numbers with no leading zeros, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Id}.
     *
     * @param id A valid ID number.
     */
    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        value = id;
    }

    /**
     * Returns true if a given string is a valid ID number.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Id // instanceof handles nulls
                && value.equals(((Id) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
