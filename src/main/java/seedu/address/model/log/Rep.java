package seedu.address.model.log;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Log's phone number in the log book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRep(String)}
 */
public class Rep {


    public static final String MESSAGE_CONSTRAINTS =
            "Reps should only contain numbers, and it should be at least 1 digit long";
    public static final String VALIDATION_REGEX = "\\d{1,}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param reps The number of reps of exercise.
     */
    public Rep(String reps) {
        requireNonNull(reps);
        checkArgument(isValidRep(reps), MESSAGE_CONSTRAINTS);
        value = reps;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidRep(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public int getReps() {
        return parseInt(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rep // instanceof handles nulls
                && value.equals(((Rep) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
