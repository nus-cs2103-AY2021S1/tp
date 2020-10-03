package seedu.address.model.exercise;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.IntegerUtil.requirePositiveInteger;

import java.util.Objects;

import seedu.address.model.util.Name;

/**
 * Represents a Exercise in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Exercise {

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
