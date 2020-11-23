package seedu.tr4cker.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.tr4cker.commons.core.GuiSettings;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Task;

/** The API of the Model component. */
public interface Model {
    /** {@code Predicate} that always evaluate to true. */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /** {@code Predicate} that evaluates to true when task is not expired. */
    Predicate<Task> PREDICATE_SHOW_PENDING_TASKS = task -> Deadline.isFutureDeadline(task.getDeadline().toString())
            && !task.isCompleted();

    /** {@code Predicate} that evaluates to true when task is expired. */
    Predicate<Task> PREDICATE_SHOW_EXPIRED_TASKS = task -> !Deadline.isFutureDeadline(task.getDeadline().toString())
            && !task.isCompleted();

    /** {@code Predicate} that evaluates to true when task is completed. */
    Predicate<Task> PREDICATE_SHOW_COMPLETED_TASKS = Task::isCompleted;

    /** {@code Predicate} that always evaluate to true. */
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

    /** {@code Predicate} that always evaluate to true. */
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true when todo is not expired. */
    Predicate<Todo> PREDICATE_SHOW_ALL_TODOS = todo -> Deadline.isFutureDeadline(todo.getDeadline().toString());

    /** Replaces user prefs data with the data in {@code userPrefs}. */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /** Returns the user prefs. */
    ReadOnlyUserPrefs getUserPrefs();

    /** Returns the user prefs' GUI settings. */
    GuiSettings getGuiSettings();

    /** Sets the user prefs' GUI settings. */
    void setGuiSettings(GuiSettings guiSettings);

    /** Returns the user prefs' Tr4cker file path. */
    Path getTr4ckerFilePath();

    /** Sets the user prefs' Tr4cker file path. */
    void setTr4ckerFilePath(Path tr4ckerFilePath);

    /** Replaces Tr4cker data with the data in {@code tr4cker}. */
    void setTr4cker(ReadOnlyTr4cker tr4cker);

    /** Returns the Tr4cker. */
    ReadOnlyTr4cker getTr4cker();

    /** Returns true if a task with the same identity as {@code task} exists in Tr4cker. */
    boolean hasTask(Task task);

    /** Returns true if a task is not done, and has the same module code as {@code module} exists in Tr4cker. */
    boolean hasRelatedIncompleteTasks(Module module);

    /**
     * Deletes the given task.
     * The task must exist in Tr4cker.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in Tr4cker.
     */
    void addTask(Task task);

    /** Returns true if a module with the same identity as {@code module} exists in Tr4cker. */
    boolean hasModule(Module module);

    /** Returns true if a {@code task} has a module code that exists in Tr4cker, or if
     * it's module field is null.
     */
    boolean hasValidModuleField(Task task);

    /**
     * Deletes the given module.
     * The module must exist in Tr4cker.
     */
    void deleteModule(Module target);

    /**
     * Adds the given module.
     * {@code module} must not already exist in Tr4cker.
     */
    void addModule(Module module);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the tr4cker.
     * The task identity of {@code editedTask} must not be the same as another existing task in Tr4cker.
     */
    void setTask(Task target, Task editedTask);

    /** Returns true if an event with the same identity as {@code event} exists in Tr4cker. */
    boolean hasEvent(Event event);

    /**
     * Deletes the given event.
     * The event must exist in Tr4cker.
     */
    void deleteEvent(Event target);

    /**
     * Adds the given event.
     * {@code event} must not already exist in Tr4cker.
     */
    void addEvent(Event event);

    /** Returns true if a todo with the same identity as {@code todo} exists in Tr4cker. */
    boolean hasTodo(Todo task);

    /**
     * Deletes the given todo.
     * The todo must exist in Tr4cker.
     */
    void deleteTodo(Todo target);

    /**
     * Adds the given todo.
     * {@code todo} must not already exist in Tr4cker.
     */
    void addTodo(Todo task);

    /**
     * Replaces the given todo {@code target} with {@code editedTodo}.
     * {@code target} must exist in the tr4cker.
     * The todo identity of {@code editedTask} must not be the same as another existing todo in Tr4cker.
     */
    void setTodo(Todo target, Todo editedTodo);

    /** Returns an unmodifiable view of the filtered task list. */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the filtered pending task list. */
    ObservableList<Task> getFilteredPendingTaskList();

    /** Returns an unmodifiable view of the filtered expired task list. */
    ObservableList<Task> getFilteredExpiredTaskList();

    /** Returns an unmodifiable view of the filtered completed task list. */
    ObservableList<Task> getFilteredCompletedTaskList();

    /** Returns an unmodifiable view of the list of modules. */
    ObservableList<Module> getFilteredModuleList();

    /** Returns an unmodifiable view of the filtered task list for PlannerDay. */
    ObservableList<Task> getPlannerFilteredTaskList();

    /** Returns an unmodifiable view of the filtered completed task list. */
    ObservableList<Event> getFilteredEventList();

    /** Returns an unmodifiable view of the filtered todo list.*/
    ObservableList<Todo> getFilteredTodoList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Updates the filter of the filtered pending task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPendingTaskList(Predicate<Task> predicate);

    /**
     * Updates the filter of the filtered expired task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExpiredTaskList(Predicate<Task> predicate);

    /**
     * Updates the filter of the filtered completed task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCompletedTaskList(Predicate<Task> predicate);

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);

    /**
     * Updates the filter of the filtered daily todo list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTodoList(Predicate<Todo> predicate);

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate} for Planner tab.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updatePlannerFilteredTaskList(Predicate<Task> predicate);

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

}
