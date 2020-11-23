package seedu.tr4cker.model.daily;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.tr4cker.model.daily.exceptions.DuplicateTodoException;
import seedu.tr4cker.model.daily.exceptions.TodoNotFoundException;


/**
 * A list of todos that enforces uniqueness between its elements and does not allow nulls.
 * An todo is considered unique by comparing using {@code Todo#isSameTodo(Todo)}. As such, adding and
 * updating of todos uses Todo#isSameTodo(Todo) for equality so as to ensure that the todo being added
 * or updated is unique in terms of identity in the UniqueTodoList. However, the removal of a todo uses
 * n#equals(Object) so as to ensure that the todo with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Todo#isSameTodo(Todo)
 */
public class UniqueDailyList implements Iterable<Todo> {
    private final ObservableList<Todo> internalList = FXCollections.observableArrayList();
    private final ObservableList<Todo> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent todo as the given argument.
     */
    public boolean contains(Todo toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTodo);
    }

    /**
     * Adds a todo to the list.
     * The todo must not already exist in the list.
     */
    public void add(Todo toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTodoException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent todo from the list.
     * The todo must exist in the list.
     */
    public void remove(Todo toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TodoNotFoundException();
        }
    }

    /**
     * Replaces the todo {@code target} in the list with {@code editedTodo}.
     * {@code target} must exist in the list.
     * The todo identity of {@code editedTodo} must not be the same as another existing todo in the list.
     */
    public void setTodo(Todo target, Todo editedTodo) {
        requireAllNonNull(target, editedTodo);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TodoNotFoundException();
        }

        if (!target.isSameTodo(editedTodo) && contains(editedTodo)) {
            throw new DuplicateTodoException();
        }

        internalList.set(index, editedTodo);
    }

    /**
     * Replaces the contents of this list with another {@code UniqueDailyList}.
     */
    public void setTodos(UniqueDailyList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code todos}.
     * {@code todos} must not contain duplicate todos.
     */
    public void setTodos(List<Todo> todos) {
        requireAllNonNull(todos);
        if (!todosAreUnique(todos)) {
            throw new DuplicateTodoException();
        }

        internalList.setAll(todos);
    }

    /**
     * Sorts the the list according to deadline
     */
    public void sortTodosAccordingToDeadline() {
        Collections.sort(internalList, new Comparator<Todo>() {
            @Override
            public int compare(Todo task1, Todo task2) {
                try {
                    return task1.getDeadline().getDateTime().compareTo(task2.getDeadline().getDateTime());
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Todo> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Todo> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueDailyList // instanceof handles nulls
                && internalList.equals(((UniqueDailyList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code todos} contains only unique todos.
     */
    private boolean todosAreUnique(List<Todo> todos) {
        for (int i = 0; i < todos.size() - 1; i++) {
            for (int j = i + 1; j < todos.size(); j++) {
                if (todos.get(i).isSameTodo(todos.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
