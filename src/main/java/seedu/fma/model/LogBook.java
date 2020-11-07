package seedu.fma.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.exercise.UniqueExerciseList;
import seedu.fma.model.exercise.exceptions.ExerciseNotFoundException;
import seedu.fma.model.log.Log;
import seedu.fma.model.log.UniqueLogList;
import seedu.fma.model.util.Name;

/**
 * Wraps all data at the log-book level
 * Duplicates are not allowed (by .isSameLog comparison)
 */
public class LogBook implements ReadOnlyLogBook {
    private final UniqueLogList logs;
    private final UniqueExerciseList exercises;

    /**
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    public LogBook() {
        logs = new UniqueLogList();
        exercises = new UniqueExerciseList();
    }

    /**
     * Creates an LogBook using the Logs and Exercises in the {@code toBeCopied}
     */
    public LogBook(ReadOnlyLogBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    // list overwrite operations

    /**
     * Replaces the contents of the log list with {@code logs}.
     * {@code logs} must not contain duplicate logs.
     */
    public void setLogs(List<Log> logs) {
        this.logs.setLogs(logs);
    }

    /**
     * Replaces the contents of the exercise list with {@code exercises}.
     * {@code exercises} must not contain duplicate exercises.
     */
    public void setExercises(List<Exercise> exercises) {
        this.exercises.setExercises(exercises);
    }

    /**
     * Resets the existing data of this {@code LogBook} with {@code newData}.
     */
    public void resetData(ReadOnlyLogBook newData) {
        requireNonNull(newData);

        setLogs(newData.getLogList());
        setExercises(newData.getExerciseList());
    }

    // log-level operations

    /**
     * Returns true if a log with the same identity as {@code log} exists in the log book.
     */
    public boolean hasLog(Log log) {
        requireNonNull(log);
        return logs.contains(log);
    }

    /**
     * Adds a log to the log book.
     * The log must not already exist in the log book.
     */
    public void addLog(Log p) {
        logs.add(p);
    }

    /**
     * Replaces the given log {@code target} in the list with {@code editedLog}.
     * {@code target} must exist in the log book.
     * The log identity of {@code editedLog} must not be the same as another existing log in the log book.
     */
    public void setLog(Log target, Log editedLog) {
        requireNonNull(editedLog);

        logs.setLog(target, editedLog);
    }

    /**
     * Removes {@code key} from this {@code LogBook}.
     * {@code key} must exist in the log book.
     */
    public void removeLog(Log key) {
        logs.remove(key);
    }

    // exercise-level operations

    /**
     * Returns true if a exercise with the same identity as {@code exercise} exists in the log book.
     */
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return exercises.contains(exercise);
    }

    /**
     * Returns an existing exercise with the same Name.
     *
     * @throws ExerciseNotFoundException if no such Exercise is found.
     */
    public Exercise getExercise(Name name) throws ExerciseNotFoundException {
        for (Exercise e : this.exercises) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        throw new ExerciseNotFoundException();
    }

    /**
     * Adds an exercise to the log book.
     * The exercise must not already exist in the log book.
     */
    public void addExercise(Exercise p) {
        exercises.add(p);
    }

    /**
     * Replaces the given Exercise {@code target} in the list with {@code editedExercise}.
     * {@code target} must exist in the log book.
     * The identity of {@code editedExercise} must not be the same as another existing Exercise in the log book.
     */
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireNonNull(editedExercise);

        exercises.setExercise(target, editedExercise);
        logs.setExercise(target, editedExercise);
    }

    /**
     * Removes {@code key} from this {@code LogBook}.
     * {@code key} must exist in the log book.
     */
    public void removeExercise(Exercise key) {
        exercises.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return logs.asUnmodifiableObservableList().stream()
                .reduce("", (a, b) -> a + b.toString(), (a, b) -> a + b);
    }

    @Override
    public ObservableList<Log> getLogList() {
        return logs.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Exercise> getExerciseList() {
        return exercises.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LogBook // instanceof handles nulls
                && logs.equals(((LogBook) other).logs));
    }

    @Override
    public int hashCode() {
        return logs.hashCode();
    }
}
