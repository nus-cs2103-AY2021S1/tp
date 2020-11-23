package seedu.tr4cker.model;

import javafx.collections.ObservableList;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.task.Task;

/**
 * Unmodifiable view of Tr4cker.
 */
public interface ReadOnlyTr4cker {

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Event> getEventList();

    /**
     * Returns an unmodifiable view of the modules list.
     * This list will not contain any duplicate modules.
     */
    ObservableList<Module> getModuleList();

    /**
     * Returns an unmodifiable view of the daily todo list.
     * This list will not contain any duplicate todos.
     */
    ObservableList<Todo> getTodoList();
}
