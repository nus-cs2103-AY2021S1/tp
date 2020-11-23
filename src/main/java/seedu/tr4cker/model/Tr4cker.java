package seedu.tr4cker.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.countdown.UniqueEventList;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.daily.UniqueDailyList;
import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.module.UniqueModuleList;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.UniqueTaskList;

/**
 * Wraps all data at TR4CKER level.
 * Duplicates are not allowed (by .isSameTask comparison).
 */
public class Tr4cker implements ReadOnlyTr4cker {

    private final UniqueTaskList tasks;
    private final UniqueModuleList modules;
    private final UniqueEventList events;
    private final UniqueDailyList todos;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tasks = new UniqueTaskList();
        events = new UniqueEventList();
        modules = new UniqueModuleList();
        todos = new UniqueDailyList();
    }

    public Tr4cker() {}

    /**
     * Creates an Tr4cker using the Tasks in the {@code toBeCopied}
     */
    public Tr4cker(ReadOnlyTr4cker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }
    /**
     * Replaces the contents of the module list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Replaces the contents of the daily list with {@code todos}.
     * {@code todos} must not contain duplicate todos.
     */
    public void setTodos(List<Todo> todos) {
        this.todos.setTodos(todos);
    }

    /**
     * Resets the existing data of this {@code Tr4cker} with {@code newData}.
     */
    public void resetData(ReadOnlyTr4cker newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
        setEvents(newData.getEventList());
        setModules(newData.getModuleList());
        setTodos(newData.getTodoList());
    }

    //// task-level operations

    /**
     * Returns true if a task with the same identity as {@code task} exists in Tr4cker.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Returns true if a task is not done yet, and has the same module code as {@code module} exists in Tr4cker.
     */
    public boolean hasRelatedIncompleteTasks(Module module) {
        requireNonNull(module);
        for (Task task : tasks) {
            boolean matchFound =
                    task.getModuleCode().contains(module.moduleCode)
                    && !task.isCompleted();
            if (matchFound) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a task to Tr4cker.
     * The task must not already exist in Tr4cker.
     */
    public void addTask(Task p) {
        tasks.add(p);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in Tr4cker.
     * The task identity of {@code editedTask} must not be the same as another existing task in Tr4cker.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code Tr4cker}.
     * {@code key} must exist in Tr4cker.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    //// event-level operations

    /**
     * Returns true if a event with the same identity as {@code event} exists in Tr4cker.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Adds an event to Tr4cker.
     * The event must not already exist in Tr4cker.
     */
    public void addEvent(Event p) {
        events.add(p);
    }

    /**
     * Removes {@code key} from this {@code Tr4cker}.
     * {@code key} must exist in Tr4cker.
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    //// module-level operations

    /**
     * Returns true if a module with the same identity as {@code module} exists in Tr4cker.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Returns true if the {@code task} has a module code that exists in Tr4cker, or if
     * it's module field is null.
     */
    public boolean hasValidModuleField(Task task) {
        requireNonNull(task);
        if (task.getModuleCode().isEmpty()) {
            return true;
        }
        return task.getModuleCode().stream().anyMatch(modules::containsCode);
    }

    /**
     * Adds a module to Tr4cker.
     * The module must not already exist in Tr4cker.
     */
    public void addModule(Module m) {
        modules.add(m);
    }

    /**
     * Removes {@code key} from this {@code Tr4cker}.
     * {@code key} must exist in Tr4cker.
     */
    public void removeModule(Module key) {
        modules.remove(key);
    }

    /**
     * Returns true if a module with the same identity as {@code module} exists in Tr4cker.
     */
    public boolean hasTodo(Todo task) {
        requireNonNull(task);
        return todos.contains(task);
    }

    /**
     * Adds a todo task in Daily tab.
     * The task must not already exist in Daily tab.
     */
    public void addTodo(Todo task) {
        todos.add(task);
    }

    /**
     * Removes {@code key} from this {@code Tr4cker}.
     * {@code key} must exist in Tr4cker.
     */
    public void removeTodo(Todo key) {
        todos.remove(key);
    }

    /**
     * Replaces the given todo {@code target} in the list with {@code editedTodo}.
     * {@code target} must exist in Tr4cker.
     * The todo identity of {@code editedTask} must not be the same as another existing todo in Tr4cker.
     */
    public void setTodo(Todo target, Todo editedTodo) {
        requireNonNull(editedTodo);

        todos.setTodo(target, editedTodo);
    }

    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " tasks"
                + events.asUnmodifiableObservableList().size() + " events"
                + modules.asUnmodifiableObservableList().size() + " modules"
                + todos.asUnmodifiableObservableList().size() + " todos";
        // TODO: refine later
    }

    @Override
    public ObservableList<Task> getTaskList() {
        tasks.sortTasksAccordingToDeadline();
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Event> getEventList() {
        events.sortEventsByDate();
        return events.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Todo> getTodoList() {
        todos.sortTodosAccordingToDeadline();
        return todos.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tr4cker // instanceof handles nulls
                && tasks.equals(((Tr4cker) other).tasks)
                && events.equals(((Tr4cker) other).events))
                && modules.equals(((Tr4cker) other).modules);
    }

    @Override
    public int hashCode() {
        return tasks.hashCode() + events.hashCode() + modules.hashCode();
        // TODO: refine later

    }
}
