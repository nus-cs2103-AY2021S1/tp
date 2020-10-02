package chopchop.model.attributes;

import static java.util.Objects.requireNonNull;
import static chopchop.commons.util.AppUtil.checkArgument;

/**
 * Represents a Recipe's name in ChopChop.
 * Guarantees: immutable; is valid as declared in {@link #isValidStep(String)}
 */
public class Step {

    public static final String MESSAGE_CONSTRAINTS =
            "Recipe steps should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "^(?=\\s*\\S).*$";

    public final String step;

    /**
     * Constructs a {@code Name}.
     *
     * @param step A valid step.
     */
    public Step(String step) {
        requireNonNull(step);
        checkArgument(isValidStep(step), MESSAGE_CONSTRAINTS);
        this.step = step;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidStep(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.step;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Step // instanceof handles nulls
                && this.step.equals(((Step) other).step)); // state check
    }

    @Override
    public int hashCode() {
        return this.step.hashCode();
    }

}

