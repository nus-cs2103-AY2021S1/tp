package chopchop.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Recipe's name in Chopchop.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Step {

    public static final String MESSAGE_CONSTRAINTS =
            "Recipe steps should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[\\w\\s]+$";

    public final String step;

    /**
     * Constructs a {@code Name}.
     *
     * @param step A valid step.
     */
    public Step(String step) {
        requireNonNull(step);
        checkArgument(isValidName(step), MESSAGE_CONSTRAINTS);
        this.step = step;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return this.step;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && this.step.equals(((Step) other).step)); // state check
    }

    @Override
    public int hashCode() {
        return this.step.hashCode();
    }

}

