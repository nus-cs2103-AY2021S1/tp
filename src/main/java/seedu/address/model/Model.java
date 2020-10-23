package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.Task;
import seedu.address.model.task.deadline.Deadline;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

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
     * Returns the user prefs' PlaNus file path.
     */
    Path getPlanusFilePath();

    /**
     * Sets the user prefs' PlaNus file path.
     */
    void setPlanusFilePath(Path planusFilePath);

    /**
     * Replaces PlaNus data with the data in {@code planus}.
     */
    void setPlanus(ReadOnlyPlanus planus);

    /** Returns PlaNus */
    ReadOnlyPlanus getPlanus();

    /**
     * Returns true if a task with the same identity as {@code task} exists in PlaNus.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in PlaNus.
     */
    void deleteTask(Task[] targets);

    /**
     * Mark the given array of tasks to be done.
     * The tasks must exist in PlaNus.
     */
    void markAsDone(Deadline[] targets, int[] durations);

    /**
     * Adds the given task.
     * {@code task} must not already exist in PlaNus.
     */
    void addTask(Task task);

    /**
     * Adds the given lesson.
     * {@code lesson} must not already exist in PlaNus.
     */
    void addLesson(Lesson lesson);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in PlaNus.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task list.
     */
    void setTask(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);
}
