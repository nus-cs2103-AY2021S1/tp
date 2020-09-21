package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Project's leader in the main catalogue.
 * Guarantees: immutable; is valid as declared in {@link #isValidLeader(String)}
 */
public class Leader {


    public static final String MESSAGE_CONSTRAINTS =
            "Leaders should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Leader}.
     *
     * @param leader A valid leader.
     */
    public Leader(String leader) {
        requireNonNull(leader);
        checkArgument(isValidLeader(leader), MESSAGE_CONSTRAINTS);
        value = leader;
    }

    /**
     * Returns true if a given string is a valid leader.
     */
    public static boolean isValidLeader(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Leader // instanceof handles nulls
                && value.equals(((Leader) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
