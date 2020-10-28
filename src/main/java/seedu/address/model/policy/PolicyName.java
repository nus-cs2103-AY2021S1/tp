package seedu.address.model.policy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Policy's name.
 * Guarantees: immutable; is valid as declared in {@link #isValidPolicyName(String)}
 */
public class PolicyName {
    public static final String MESSAGE_CONSTRAINTS =
            "Policy names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code PolicyName}.
     *
     * @param policyName A valid PolicyName.
     */
    public PolicyName(String policyName) {
        requireNonNull(policyName);
        checkArgument(isValidPolicyName(policyName), MESSAGE_CONSTRAINTS);
        value = policyName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPolicyName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PolicyName // instanceof handles nulls
                && value.equals(((PolicyName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
