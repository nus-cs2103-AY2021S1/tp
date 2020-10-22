package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.task.Task;

/**
 * Unmodifiable view of a todo list
 */
public interface ReadOnlyTodoList {

    /**
     * Returns an unmodifiable view of the todo list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTodoList();
}
