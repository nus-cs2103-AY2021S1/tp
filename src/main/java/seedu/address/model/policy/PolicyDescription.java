package seedu.address.model.policy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Policy's name.
 * Guarantees: immutable; is valid as declared in {@link #isValidPolicyDescription(String)}
 */
public class PolicyDescription {
    public static final String MESSAGE_CONSTRAINTS =
            "Policy Description can take any values, should not be more than 100 characters and should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].{0,99}";

    public final String value;

    /**
     * Constructs a {@code PolicyDescription}.
     *
     * @param policyDescription A valid PolicyDescription.
     */
    public PolicyDescription(String policyDescription) {
        requireNonNull(policyDescription);
        checkArgument(isValidPolicyDescription(policyDescription), MESSAGE_CONSTRAINTS);
        value = policyDescription;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPolicyDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PolicyDescription // instanceof handles nulls
                && value.equals(((PolicyDescription) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
