package seedu.fma.model;

import javafx.collections.ObservableList;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.exercise.exceptions.ExerciseNotFoundException;
import seedu.fma.model.log.Log;
import seedu.fma.model.util.Name;

/**
 * Unmodifiable view of an log book
 */
public interface ReadOnlyLogBook {

    /**
     * Returns an unmodifiable view of the logs list.
     * This list will not contain any duplicate logs.
     */
    ObservableList<Log> getLogList();

    /**
     * Returns an unmodifiable view of the Exercise list.
     * This list will not contain any duplicate Exercises.
     */
    ObservableList<Exercise> getExerciseList();

    Exercise getExercise(Name name) throws ExerciseNotFoundException;
}
