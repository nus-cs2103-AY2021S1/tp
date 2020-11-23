package seedu.tr4cker.model;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.tr4cker.commons.core.GuiSettings;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Task;

/**
 * Represents the in-memory model of TR4CKER data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Tr4cker tr4cker;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Task> filteredPendingTasks;
    private final FilteredList<Task> filteredExpiredTasks;
    private final FilteredList<Task> filteredCompletedTasks;
    private final FilteredList<Module> filteredModules;
    private final FilteredList<Task> plannerFilteredTasks;
    private final FilteredList<Event> filteredEvents;
    private final FilteredList<Todo> filteredTodos;

    /**
     * Initializes a ModelManager with the given tr4cker and userPrefs.
     */
    public ModelManager(ReadOnlyTr4cker tr4cker, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(tr4cker, userPrefs);

        logger.fine("Initializing with TR4CKER: " + tr4cker + " and user prefs " + userPrefs);

        this.tr4cker = new Tr4cker(tr4cker);
        this.userPrefs = new UserPrefs(userPrefs);

        filteredTasks = new FilteredList<>(this.tr4cker.getTaskList());
        filteredPendingTasks = new FilteredList<>(this.tr4cker.getTaskList().filtered(
            x -> Deadline.isFutureDeadline(x.getDeadline().toString()) && !x.isCompleted()));
        filteredExpiredTasks = new FilteredList<>(this.tr4cker.getTaskList().filtered(
            x -> !Deadline.isFutureDeadline(x.getDeadline().toString()) && !x.isCompleted()));
        filteredCompletedTasks = new FilteredList<>(this.tr4cker.getTaskList().filtered(
            Task::isCompleted));

        filteredModules = new FilteredList<>(this.tr4cker.getModuleList().filtered(x -> true));

        plannerFilteredTasks = new FilteredList<>(this.tr4cker.getTaskList());

        filteredEvents = new FilteredList<>(this.tr4cker.getEventList());

        filteredTodos = new FilteredList<>(this.tr4cker.getTodoList().filtered(
            x -> Deadline.isFutureDeadline(x.getDeadline().toString())));

    }

    public ModelManager() {
        this(new Tr4cker(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTr4ckerFilePath() {
        return userPrefs.getTr4ckerFilePath();
    }

    @Override
    public void setTr4ckerFilePath(Path tr4ckerFilePath) {
        requireNonNull(tr4ckerFilePath);
        userPrefs.setTr4ckerFilePath(tr4ckerFilePath);
    }

    //=========== TR4CKER ================================================================================

    @Override
    public void setTr4cker(ReadOnlyTr4cker tr4cker) {
        this.tr4cker.resetData(tr4cker);
    }

    @Override
    public ReadOnlyTr4cker getTr4cker() {
        return tr4cker;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tr4cker.hasTask(task);
    }

    @Override
    public boolean hasRelatedIncompleteTasks(Module module) {
        return tr4cker.hasRelatedIncompleteTasks(module);
    }

    @Override
    public void deleteTask(Task target) {
        tr4cker.removeTask(target);
    }

    @Override
    public void addTask(Task task) {
        tr4cker.addTask(task);
        updateFilteredPendingTaskList(PREDICATE_SHOW_PENDING_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        tr4cker.setTask(target, editedTask);
    }

    @Override
    public boolean hasEvent(Event task) {
        requireNonNull(task);
        return tr4cker.hasEvent(task);
    }

    @Override
    public void deleteEvent(Event target) {
        tr4cker.removeEvent(target);
    }

    @Override
    public void addEvent(Event task) {
        tr4cker.addEvent(task);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return tr4cker.hasModule(module);
    }

    @Override
    public boolean hasValidModuleField(Task task) {
        return tr4cker.hasValidModuleField(task);
    }

    @Override
    public void deleteModule(Module target) {
        tr4cker.removeModule(target);
    }

    @Override
    public void addModule(Module module) {
        tr4cker.addModule(module);
    }

    @Override
    public boolean hasTodo(Todo task) {
        requireNonNull(task);
        return tr4cker.hasTodo(task);
    }

    @Override
    public void deleteTodo(Todo target) {
        tr4cker.removeTodo(target);
    }

    @Override
    public void addTodo(Todo task) {
        tr4cker.addTodo(task);
        updateFilteredTodoList(PREDICATE_SHOW_ALL_TODOS);
    }

    @Override
    public void setTodo(Todo target, Todo editedTodo) {
        requireAllNonNull(target, editedTodo);
        tr4cker.setTodo(target, editedTodo);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task}
     * backed by the internal list of {@code versionedTr4cker}.
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Task}
     * backed by the internal list of {@code versionedTr4cker}.
     */
    @Override
    public ObservableList<Task> getFilteredPendingTaskList() {
        return filteredPendingTasks;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Task}
     * backed by the internal list of {@code versionedTr4cker}.
     */
    @Override
    public ObservableList<Task> getFilteredExpiredTaskList() {
        return filteredExpiredTasks;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Task}
     * backed by the internal list of {@code versionedTr4cker}.
     */
    @Override
    public ObservableList<Task> getFilteredCompletedTaskList() {
        return filteredCompletedTasks;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Module}
     * backed by the internal list of {@code versionedTr4cker}.
     */
    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Task}
     * backed by the internal list of {@code versionedTr4cker} for PlannerDay.
     * Should only show current's day tasks by default.
     */
    @Override
    public ObservableList<Task> getPlannerFilteredTaskList() {
        return plannerFilteredTasks;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Event}
     * backed by the internal list of {@code versionedTr4cker}.
     */
    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Todo}
     * backed by the internal list of {@code versionedTr4cker}.
     */
    @Override
    public ObservableList<Todo> getFilteredTodoList() {
        return filteredTodos;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public void updateFilteredPendingTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredPendingTasks.setPredicate(predicate);
    }

    @Override
    public void updateFilteredExpiredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredExpiredTasks.setPredicate(predicate);
    }

    @Override
    public void updateFilteredCompletedTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredCompletedTasks.setPredicate(predicate);
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
    }

    @Override
    public void updateFilteredTodoList(Predicate<Todo> predicate) {
        requireNonNull(predicate);
        filteredTodos.setPredicate(predicate);
    }

    @Override
    public void updatePlannerFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        plannerFilteredTasks.setPredicate(predicate);
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return tr4cker.equals(other.tr4cker)
                && userPrefs.equals(other.userPrefs)
                && filteredPendingTasks.equals(other.filteredPendingTasks)
                && plannerFilteredTasks.equals(other.plannerFilteredTasks)
                && filteredEvents.equals(other.filteredEvents);
    }

}
