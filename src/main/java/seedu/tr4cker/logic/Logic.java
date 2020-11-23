package seedu.tr4cker.logic;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.tr4cker.commons.core.GuiSettings;
import seedu.tr4cker.logic.commands.CommandResult;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.logic.parser.exceptions.ParseException;
import seedu.tr4cker.model.ReadOnlyTr4cker;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.TaskDueInPredicate;

/** API of the Logic component. */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the Tr4cker.
     *
     * @see seedu.tr4cker.model.Model#getTr4cker()
     */
    ReadOnlyTr4cker getTr4cker();

    /** Returns an unmodifiable view of the filtered list of tasks. */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the filtered list of pending tasks. */
    ObservableList<Task> getFilteredPendingTaskList();

    /** Returns an unmodifiable view of the filtered list of expired tasks. */
    ObservableList<Task> getFilteredExpiredTaskList();

    /** Returns an unmodifiable view of the filtered list of completed tasks. */
    ObservableList<Task> getFilteredCompletedTaskList();

    /** Returns an unmodifiable view of the list of modules. */
    ObservableList<Module> getFilteredModuleList();

    /** Returns an unmodifiable view of the filtered list of tasks for PlannerDay. */
    ObservableList<Task> getPlannerFilteredTaskList();

    /** Returns an unmodifiable view of the filtered list of events. */
    ObservableList<Event> getFilteredEventList();

    /** Returns an unmodifiable view of the filtered list of todos. */
    ObservableList<Todo> getFilteredTodoList();

    /** Updates an unmodifiable view of the filtered list of tasks for Module tab. */
    void updateFilteredModuleList(Predicate<Module> predicate);

    /** Updates an unmodifiable view of the filtered list of tasks for Daily tab. */
    void updateFilteredTodoList(Predicate<Todo> predicate);

    /** Updates an unmodifiable view of the filtered list of tasks for Planner tab. */
    void updatePlannerFilteredTaskList(TaskDueInPredicate taskDueInPredicate);

    /** Returns the user prefs' tr4cker file path. */
    Path getTr4ckerFilePath();

    /** Returns the user prefs' GUI settings. */
    GuiSettings getGuiSettings();

    /** Set the user prefs' GUI settings. */
    void setGuiSettings(GuiSettings guiSettings);
}
