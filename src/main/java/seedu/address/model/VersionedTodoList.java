package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.exceptions.VersionedListException;

/**
 * Class that stores versioned history and future of a todo list used for undo/redo functions
 */
public class VersionedTodoList extends TodoList {
    private static final String MESSAGE_NO_REDO_HISTORY = "There are no Todo List commands to redo";
    private static final String MESSAGE_NO_UNDO_HISTORY = "There are no Todo List commands to undo";
    private List<ReadOnlyTodoList> todoListStateList = new ArrayList<>();
    private int currentStatePointer;

    /**
     * Creates a versioned todo list with an empty initial todo list
     */
    public VersionedTodoList() {
        super();
        todoListStateList.add(new TodoList());
        this.currentStatePointer = 0;
    }

    /**
     * Creates a versioned todo list using the todo lists in the {@code toBeCopied}
     * @param toBeCopied
     */
    public VersionedTodoList(ReadOnlyTodoList toBeCopied) {
        super(toBeCopied);
        todoListStateList.add(toBeCopied);
        this.currentStatePointer = 0;
    }
    /**
     * Saves the current todo list state in history.
     */
    public void commit(ReadOnlyTodoList todoList) {
        todoListStateList.subList(this.currentStatePointer + 1, todoListStateList.size()).clear();
        todoListStateList.add(new TodoList(todoList));
        this.currentStatePointer += 1;
        super.resetData(todoList);
    }

    /**
     * Restores the previous todo list state from history.
     */
    public void undo() throws VersionedListException {
        if (isIndexZero()) {
            throw new VersionedListException(MESSAGE_NO_UNDO_HISTORY);
        }
        this.currentStatePointer -= 1;
        super.resetData(todoListStateList.get(currentStatePointer));
    }

    /**
     * Restores the previously undone todo list state from history.
     */
    public void redo() throws VersionedListException {
        if (isLastIndex()) {
            throw new VersionedListException(MESSAGE_NO_REDO_HISTORY);
        }
        assert !isLastIndex() : "Assertion error, there are no instructions to redo";
        this.currentStatePointer += 1;
        super.resetData(todoListStateList.get(currentStatePointer));
    }

    /**
     * Returns true if state pointer is at 0.
     */
    public boolean isIndexZero() {
        return currentStatePointer == 0;
    }

    /**
     * Returns true if state pointer greater than the size of the eventList state list
     */
    public boolean isLastIndex() {
        return currentStatePointer >= todoListStateList.size() - 1;
    }
}
