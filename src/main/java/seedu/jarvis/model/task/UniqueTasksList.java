package seedu.jarvis.model.task;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.jarvis.model.task.exceptions.TaskNotFoundException;

public class UniqueTasksList {
    private final ObservableList<Task> internalList = FXCollections.observableArrayList();

    /**
     * Adds a mission to the list.
     */
    public void add(Task toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent task from the list.
     * The task must exist in the list.
     */
    public void remove(Task toRemove) throws TaskNotFoundException {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    public void setTasks(List<Task> tasks) {
        this.internalList.setAll(tasks);
    }

    public ObservableList<Task> asObservableList() {
        return internalList;
    }

    /**
     * Returns true if the list contains an equivalent student as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }
}
