package seedu.address.model.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a MuscleTag in the exercise book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidMuscleTagName(String)}
 */
public class MuscleTag {

    public static final String MESSAGE_CONSTRAINTS = "Muscle tag names should only contain alphabets";
    public static final String VALIDATION_REGEX = "\\p{Alpha}+";

    public final String muscleTagName;

    /**
     * Constructs a {@code MuscleTag}.
     *
     * @param muscleTagName A valid muscle tag name.
     */
    public MuscleTag(String muscleTagName) {
        requireNonNull(muscleTagName);
        checkArgument(isValidMuscleTagName(muscleTagName), MESSAGE_CONSTRAINTS);
        this.muscleTagName = muscleTagName;
    }

    /**
     * Returns true if a given string is a valid muscle tag name.
     */
    public static boolean isValidMuscleTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MuscleTag // instanceof handles nulls
                && muscleTagName.equals(((MuscleTag) other).muscleTagName)); // state check
    }

    @Override
    public int hashCode() {
        return muscleTagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + muscleTagName + ']';
    }

}
