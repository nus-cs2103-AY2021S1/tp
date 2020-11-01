package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS = "Remarks should not be more than 100 characters in length.";

    private static final Integer MAX_REMARK_LENGTH = 100;

    public final String value;

    /**
     * Constructs an {@code Remark}.
     *
     * @param remark A remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        checkArgument(isValidRemark(remark), MESSAGE_CONSTRAINTS);
        value = remark;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns true if a given remark is a valid remark.
     */
    public static boolean isValidRemark(String test) {
        return test.length() <= MAX_REMARK_LENGTH;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
