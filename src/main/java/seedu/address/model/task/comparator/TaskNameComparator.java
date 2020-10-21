package seedu.address.model.task.comparator;

import java.util.Comparator;

import seedu.address.model.task.TaskName;

/**
 * Comparator to compare two tasks name.
 */
public class TaskNameComparator implements Comparator<TaskName> {
    /**
     * Compare two TaskName lexicographically.
     *
     * @param name first task name
     * @param otherName second task name
     * @return -1 if value of name is lexicographically less than value of otherName,
     *         0 if both task name are the same, and 1 if value of name is lexicographically
     *         greater than value of otherName
     */
    @Override
    public int compare(TaskName name, TaskName otherName) {
        return name.getValue().compareTo(otherName.getValue());
    }
}
