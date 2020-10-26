package seedu.address.model;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Template;

/**
 * The API of the Model component.
 */
public interface ExerciseModel {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Exercise> PREDICATE_SHOW_ALL_EXERCISE = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getExerciseBookFilePath();

    /**
     * Returns the HashMap that contains the amount of calories burnt per day.
     */
    HashMap<String, Integer> getCaloriesByDay();

    /**
     * Sets the user prefs' address book file path.
     */
    void setExerciseBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setExerciseBook(ReadOnlyExerciseBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyExerciseBook getExerciseBook();

    /**
     * Returns true if a Exercise with the same identity as {@code Exercise} exists in the address book.
     */
    boolean hasExercise(Exercise exercise);

    /**
     * Deletes the given exercise.
     * The exercise must exist in the address book.
     */
    void deleteExercise(Exercise target);

    /**
     * Adds the given exercise.
     * {@code exercise} must not already exist in the address book.
     */
    void addExercise(Exercise exercise);


    void addTemplate(Template template);

    /**
     * Replaces the given Exercise {@code target} with {@code editedExercise}.
     * {@code target} must exist in the address book.
     * The Exercise identity of {@code editedExercise} must not be the
     * same as another existing Exercise in the exercise book.
     */
    void setExercise(Exercise target, Exercise editedExercise);

    /**
     * Returns an unmodifiable view of the filtered Exercise list
     */
    ObservableList<Exercise> getFilteredExerciseList();

    /**
     * Updates the filter of the filtered Exercise list to filter by the given {@code predicate}.
     * @param predicate
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExerciseList(Predicate<Exercise> predicate);

    /**
     * Save to the specified File
     */
    void archive(Path path);
}
