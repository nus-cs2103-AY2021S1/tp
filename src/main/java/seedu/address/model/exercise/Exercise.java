package seedu.address.model.exercise;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents an exercise in the exercise book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Exercise {
    // Identity fields
    private final Name name;
    private final Date date;

    // Data fields
    // Functional dependencies:
    // Name -> calories (hence left out in equality checks)
    private final Description description;
    private final Calories calories;
    private final Set<MuscleTag> muscleTags = new HashSet<>();
    private final Set<ExerciseTag> tags = new HashSet<>();

    /**
     * Every field except for calories must be present and not null.
     */
    public Exercise(Name name, Description description, Date date,
                    Calories calories, Set<MuscleTag> muscleTags, Set<ExerciseTag> tags) {
        requireAllNonNull(name, description, muscleTags, tags);
        this.name = name;
        this.description = description;
        this.date = date;
        this.calories = calories;
        this.muscleTags.addAll(muscleTags);
        this.tags.addAll(tags);
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

    /**
     * Returns an immutable muscleTag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<MuscleTag> getMuscleTags() {
        return Collections.unmodifiableSet(muscleTags);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ExerciseTag> getExerciseTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, date, muscleTags, tags);
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
                .append(" Muscles worked:");

        getMuscleTags().forEach(builder::append);

        builder.append(" Tags: ");

        getExerciseTags().forEach(builder::append);

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
                && otherExercise.getExerciseTags().equals(getExerciseTags())
                && otherExercise.getMuscleTags().equals(getMuscleTags());
    }

    /**
     * Returns true if both exercises of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two exercises.
     */
    public boolean isSameExercise(Exercise otherExercise) {
        return equals(otherExercise);
    }
}
