package seedu.address.model.exercise;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

public class Exercise {
    // identity field
    private final Name name;
    private final Date date;
    // data field
    private final Description description;
    private final Calories calories;

    /**
     * Every field except for calories must be present and not null.
     */
    public Exercise(Name name, Description description, Date date, Calories calories) {
        requireAllNonNull(name, description, date);
        this.name = name;
        this.description = description;
        this.date = date;
        this.calories = calories;
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

    @Override
    public int hashCode() {
        return Objects.hash(name, description, date, calories);
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
                .append(getCalories());
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
                && otherExercise.getDate().equals(getDate())
                && otherExercise.getCalories().equals(getCalories());
    }

    public boolean isSameExercise(Exercise other) {
        return this.equals(other);
    }

}
