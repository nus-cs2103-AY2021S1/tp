package seedu.address.model.routine;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.exercise.Exercise;
import seedu.address.model.timetable.Activity;
import seedu.address.model.util.Name;

public class Routine extends Activity {

    private final Set<Exercise> exercises;

    /**
     * Every field must be present and not null.
     */
    public Routine(Name name) {
        super(name);
        this.exercises = new HashSet<>();
    }

    public Set<Exercise> getExercises() {
        return this.exercises;
    }

    /**
     * Adds an Exercise into the Routine object.
     * @param newExercise an existing Exercise in fitNUS.
     */
    public void addExercise(Exercise newExercise) {
        assert(!this.exercises.contains(newExercise));
        this.exercises.add(newExercise);
    }

    /**
     * Deletes an Exercise from the Routine object.
     * @param oldExercise an existing Exercise in this particular Routine object.
     */
    public void deleteExercise(Exercise oldExercise) {
        assert(this.exercises.contains(oldExercise));
        this.exercises.remove(oldExercise);
    }

    /**
     * Checks if the input Exercise exists within this Routine.
     * @param exercise the possible Exercise in this particular Routine object.
     */
    public boolean hasExercise(Exercise exercise) {
        return this.exercises.contains(exercise);
    }

    /**
     * Returns true if both routines of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two routines.
     */
    @Override
    public boolean isSameActivity(Activity otherActivity) {
        if (otherActivity == this) {
            return true;
        }

        if (!(otherActivity instanceof Routine)) {
            return false;
        }

        Routine otherRoutine = (Routine) otherActivity;
        return otherRoutine.getName().equals(getName());
    }

    @Override
    public boolean isRoutine() {
        return true;
    }

    /**
     * Returns true if both routines have the same identity and data fields.
     * This defines a stronger notion of equality between two routines.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Routine)) {
            return false;
        }

        Routine otherPerson = (Routine) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getExercises().equals(getExercises());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, exercises);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        for (Exercise exercise : exercises) {
            builder.append("\n");
            builder.append(exercise.toString());
        }
        return builder.toString();
    }

}
