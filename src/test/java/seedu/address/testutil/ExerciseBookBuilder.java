package seedu.address.testutil;

import seedu.address.model.ExerciseBook;
import seedu.address.model.exercise.Exercise;

/**
 * A utility class to help with building Exercisebook objects.
 * Example usage: <br>
 * {@code ExerciseBook ab = new ExerciseBookBuilder().withPerson("John", "Doe").build();}
 */
public class ExerciseBookBuilder {

    private ExerciseBook exerciseBook;

    public ExerciseBookBuilder() {
        exerciseBook = new ExerciseBook();
    }

    public ExerciseBookBuilder(ExerciseBook exerciseBook) {
        this.exerciseBook = exerciseBook;
    }

    /**
     * Adds a new {@code Person} to the {@code ExerciseBook} that we are building.
     */
    public ExerciseBookBuilder withExercise(Exercise exercise) {
        exerciseBook.addExercise(exercise);
        return this;
    }

    public ExerciseBook build() {
        return exerciseBook;
    }

}
