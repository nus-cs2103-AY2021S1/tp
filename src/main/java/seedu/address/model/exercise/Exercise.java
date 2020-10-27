package seedu.address.model.exercise;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents an exercise in the exercise book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Exercise {
    // Identity fields
    private final Name name;
    private final Date date;

    // Data fields
    // FDs
    // Name -> musclesWorked, calories (hence left out in equality checks)
    private final Description description;
    private final Calories calories;
    private final List<Muscle> musclesWorked;

    /**
     * Every field except for calories must be present and not null.
     */
    public Exercise(Name name, Description description, Date date, Calories calories, List<Muscle> musclesWorked) {
        requireAllNonNull(name, description, date, calories);
        this.name = name;
        this.description = description;
        this.date = date;
        this.calories = calories;
        this.musclesWorked = musclesWorked;
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Calories getCalories() {
        return calories;
    }

    public List<Muscle> getMusclesWorked() {
        return musclesWorked;
    }

    public String getMusclesWorkedDescription() {
        return Muscle.muscleListToString(musclesWorked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, date);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getName())
                .append(" Description: ")
                .append(getDescription())
                .append(" Date: ")
                .append(getDate())
                .append(" Calories: ")
                .append(getCalories())
                .append(" Muscles worked:")
                .append(getMusclesWorkedDescription());

        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Exercise)) {
            return false;
        }

        Exercise otherExercise = (Exercise) other;

        return otherExercise.getName().equals(getName())
                && otherExercise.getDescription().equals(getDescription())
                && otherExercise.getDate().equals(getDate());
    }

    /**
     * Returns true if both exercises of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two exercises.
     */
    public boolean isSameExercise(Exercise otherExercise) {
        return equals(otherExercise);
    }
}
