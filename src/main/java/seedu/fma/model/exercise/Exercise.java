package seedu.fma.model.exercise;

import static seedu.fma.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.fma.model.util.Calories;
import seedu.fma.model.util.Name;

/**
 * Represents a Exercise in the log book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Exercise {
    public static final String MESSAGE_CONSTRAINTS =
            "Exercise does not exist";

    // Identity fields
    private final Name name;
    private final Calories caloriesPerRep;

    /**
     * Every field must be present and not null.
     */
    public Exercise(Name name, Calories caloriesPerRep) {
        requireAllNonNull(name, caloriesPerRep);
        this.name = name;
        this.caloriesPerRep = caloriesPerRep;
    }

    public Name getName() {
        return name;
    }

    public Calories getCaloriesPerRep() {
        return caloriesPerRep;
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

        return ((Exercise) other).getName().equals(getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, caloriesPerRep);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: " + getName() + "\n")
                .append("Calories per rep: ")
                .append(getCaloriesPerRep());
        return builder.toString();
    }
}
