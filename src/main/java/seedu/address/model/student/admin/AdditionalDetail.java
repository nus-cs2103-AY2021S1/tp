package seedu.address.model.student.admin;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an additional detail in Reeve.
 * Guarantees: immutable; detail is valid as declared in {@link #isValidAdditionalDetail(String)}
 */
public class AdditionalDetail {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String detail;

    /**
     * Constructs a {@code AdditionalDetail}.
     *
     * @param detail A valid detail.
     */
    public AdditionalDetail(String detail) {
        requireNonNull(detail);
        checkArgument(isValidAdditionalDetail(detail), MESSAGE_CONSTRAINTS);
        this.detail = detail;
    }

    /**
     * Returns true if a given string is a valid additional detail.
     */
    public static boolean isValidAdditionalDetail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AdditionalDetail // instanceof handles nulls
                && detail.equals(((AdditionalDetail) other).detail)); // state check
    }

    @Override
    public int hashCode() {
        return detail.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + detail + ']';
    }

}