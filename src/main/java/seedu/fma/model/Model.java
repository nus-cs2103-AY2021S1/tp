package seedu.fma.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.fma.commons.core.GuiSettings;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.log.Log;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Log> PREDICATE_SHOW_ALL_LOGS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' log book file path.
     */
    Path getLogBookFilePath();

    /**
     * Sets the user prefs' log book file path.
     */
    void setLogBookFilePath(Path logBookFilePath);

    /**
     * Replaces log book data with the data in {@code logBook}.
     */
    void setLogBook(ReadOnlyLogBook logBook);

    /** Returns the LogBook */
    ReadOnlyLogBook getLogBook();

    /**
     * Returns true if a log with the same identity as {@code log} exists in the log book.
     */
    boolean hasLog(Log log);

    /**
     * Deletes the given log.
     * The log must exist in the log book.
     */
    void deleteLog(Log target);

    /**
     * Adds the given log.
     * {@code log} must not already exist in the log book.
     */
    void addLog(Log log);

    /**
     * Replaces the given log {@code target} with {@code editedLog}.
     * {@code target} must exist in the log book.
     * The log identity of {@code editedLog} must not be the same as another existing log in the log book.
     */
    void setLog(Log target, Log editedLog);

    /**
     * Returns true if a exercise with the same identity as {@code exercise} exists in the log book.
     */
    boolean hasExercise(Exercise exercise);

    /**
     * Deletes the given exercise.
     * The exercise must exist in the log book.
     */
    void deleteExercise(Exercise target);

    /**
     * Adds the given exercise.
     * {@code exercise} must not already exist in the log book.
     */
    void addExercise(Exercise exercise);

    /**
     * Replaces the given exercise {@code target} with {@code editedExercise}.
     * {@code target} must exist in the log book.
     * The exercise identity of {@code editedExercise} must not be the same as another
     * existing exercise in the log book.
     */
    void setExercise(Exercise target, Exercise editedExercise);

    /** Returns an unmodifiable view of the filtered log list */
    ObservableList<Log> getFilteredLogList();

    /** Returns an unmodifiable view of the filtered exercise list */
    ObservableList<Exercise> getFilteredExerciseList();

    /**
     * Updates the filter of the filtered log list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredLogList(Predicate<Log> predicate);
}
