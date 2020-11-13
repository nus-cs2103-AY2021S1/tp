package seedu.address.model.calorie;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Output exercise
 */
public class Exercise {
    public static final String MESSAGE_CONSTRAINTS =
            "Exercise can take any values, and it should not be blank";

    public static final String MESSAGE_CONSTRAINTS_WRONG_TYPE =
            "Exercise can only be used with Calorie Type: Output";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String exercise;

    /**
     * Constructs a {@code Exercise}.
     *
     * @param exercise A valid exercise.
     */
    public Exercise(String exercise) {
        requireNonNull(exercise);
        checkArgument(isValidExercise(exercise), MESSAGE_CONSTRAINTS);
        this.exercise = exercise;
    }
    /**
     * check if test is a valid exercise
     *
     * @param test
     */
    public static boolean isValidExercise(String test) {
        assert test != null : "test must not be empty";
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return exercise;
    }

    /**
     * Returns true if both Exercises have the same exercise string.
     * This defines a stronger notion of equality between two Exercises.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Exercise)) {
            return false;
        }

        Exercise otherExercise = (Exercise) other;
        return otherExercise.exercise.equals(this.exercise);
    }

}
