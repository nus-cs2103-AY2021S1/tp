package seedu.address.model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.parser.exceptions.CaloriesOverflow;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Template;
import seedu.address.model.goal.Goal;

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
     * Returns the user prefs' exercise book file path.
     */
    Path getExerciseBookFilePath();

    Path getGoalBookFilePath();

    HashMap<String, Integer> getCaloriesByDay();


    /**
     * Sets the user prefs' exercise book file path.
     */
    void setExerciseBookFilePath(Path exerciseBookFilePath);

    /**
     * Sets the user prefs' goal book file path.
     */
    void setGoalBookFilePath(Path goalBookFilePath);

    /**
     * Replaces exercise book data with the data in {@code exerciseBook}.
     */
    void setExerciseBook(ReadOnlyExerciseBook exerciseBook);


    /**
     * Replaces goal book data with the data in {@code goalBook}.
     */
    void setGoalBook(ReadOnlyGoalBook goalBook);

    /**
      * Returns the ExerciseBook.
     */
    ReadOnlyExerciseBook getExerciseBook();

    /**
     * Returns the GoalBook.
     */
    ReadOnlyGoalBook getGoalBook();

    /**
     * Returns true if a Exercise with the same identity as {@code Exercise} exists in the exercise book.
     */
    boolean hasExercise(Exercise exercise);

    /**
     * Returns true if a goal with the same identity as {@code goal} exists in the goal book.
     */
    boolean hasGoal(Goal goal);

    /**
     * Deletes the given exercise.
     * The exercise must exist in the exercise book.
     */
    void deleteExercise(Exercise target);


    /**
     * Deletes the given goal.
     * The goal must exist in the exercise book.
     */
    void deleteGoal(Goal target);

    /**
     * Return true if the new exercise will cause Integer Overflow.
     * An overflow occurs when the calories burnt from all the exercises done for the
     * day is more than INTEGER.MAX_VALUE.
     * @param e The new exercise to be added.
     */
    boolean checkOverflow(Exercise e);

    /**
     * Return true if the new exercise that will replace the old exercise will cause Integer Overflow.
     * An overflow occurs when the calories burnt from all the exercises done for the
     * day is more than INTEGER.MAX_VALUE.
     * @param oldE The exercise to be replaced.
     * @param newE The exercise to be replace the oldE.
     */
    boolean checkOverflow(Exercise oldE, Exercise newE);

    /**
     * Adds the given exercise.
     * {@code exercise} must not already exist in the exercise book.
     */
    Optional<Goal> addExercise(Exercise exercise) throws CaloriesOverflow;

    /**
     * Adds the given template.
     * {@code template} must not already exist in the exericse book.
     */
    void addTemplate(Template template);

    void resetAll() throws IOException;

    /**
     * Adds the given goal.
     * {@code exercise} must not already exist in the address book.
     */
    void addGoal(Goal goal);


    /**
     * Replaces the given Exercise {@code target} with {@code editedExercise}.
     * {@code target} must exist in the exercise book.
     * The Exercise identity of {@code editedExercise} must not be the
     * same as another existing Exercise in the exercise book.
     */
    void setExercise(Exercise target, Exercise editedExercise);

    /**
     * Returns an unmodifiable view of the filtered Exercise list
     */
    ObservableList<Exercise> getFilteredExerciseList();

    /**
     * Replaces the given Exercise {@code target} with {@code editedExercise}.
     * {@code target} must exist in the address book.
     * The Exercise identity of {@code editedExercise} must not be the
     * same as another existing Exercise in the exercise book.
     */
    void setGoal(Goal target, Goal editedGoal);


    /**
     * Updates the filter of the filtered Exercise list to filter by the given {@code predicate}.
     * @param predicate
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExerciseList(Predicate<Exercise> predicate);

    /**
     * Returns the filtered Template list
     */
    ObservableList<Template> getFilteredTemplateList();

    /**
     * Save to the specified File
     */
    void archive(Path path);
}
