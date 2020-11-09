package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

//@@author w-yeehong
/**
 * Represents a description of a Task in the app.
 * Guarantees: immutable
 */
public class Description {

    public static final int MINIMUM_LENGTH = 1;

    public static final int MAXIMUM_LENGTH = 4000;

    public static final String MESSAGE_CONSTRAINTS = "The description of a task must be between "
            + MINIMUM_LENGTH + " and " + MAXIMUM_LENGTH + " characters long.";

    public final String value;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A description of the Task.
     */
    public Description(String description) {
        requireNonNull(description);
        assert isValidDescription(description) : MESSAGE_CONSTRAINTS;
        value = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.length() >= MINIMUM_LENGTH && test.length() <= MAXIMUM_LENGTH;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && value.equals(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
