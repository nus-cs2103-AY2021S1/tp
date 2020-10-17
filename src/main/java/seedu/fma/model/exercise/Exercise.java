package seedu.fma.model.exercise;

import static seedu.fma.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.fma.commons.util.IntegerUtil.requirePositiveInteger;
import static seedu.fma.model.util.SampleDataUtil.getSampleExercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import seedu.fma.model.exercise.exceptions.ExerciseNotFoundException;
import seedu.fma.model.log.Log;
import seedu.fma.model.util.Name;
import seedu.fma.model.util.SampleDataUtil;

/**
 * Represents a Exercise in the log book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Exercise {
    public static final String MESSAGE_CONSTRAINTS =
            "Exercise does not exist";

    // Identity fields
    private final Name name;
    private final int caloriesPerRep;

    /**
     * Every field must be present and not null.
     */
    public Exercise(Name name, int caloriesPerRep) {
        requireAllNonNull(name, caloriesPerRep);
        requirePositiveInteger(caloriesPerRep);
        this.name = name;
        this.caloriesPerRep = caloriesPerRep;
    }

    public Name getName() {
        return name;
    }

    public int getCaloriesPerRep() {
        return caloriesPerRep;
    }

    // TODO GET RID OF THIS METHOD and use LogBook.getExercise() instead
    /**
     * Returns an existing exercise with the same Name.
     *
     * @throws ExerciseNotFoundException if no such Exercise is found.
     */
    public static Exercise getExercise(Name name) throws ExerciseNotFoundException {
        for (Exercise e : getSampleExercises()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        throw new ExerciseNotFoundException();
    }

    /**
     * Returns true if both Exercises have the same Name. This defines a weaker
     * notion of equality between two Exercises.
     */
    public boolean isSameExercise(Exercise otherExercise) {
        if (otherExercise == this) {
            return true;
        }

        return otherExercise != null
                && otherExercise.getName().equals(getName());
    }

    /**
     * Returns true if both exercises have the same identity and data fields.
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
        return otherExercise != null
                && otherExercise.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, caloriesPerRep);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" CaloriesPerRep: ")
                .append(getCaloriesPerRep());
        return builder.toString();
    }
}
