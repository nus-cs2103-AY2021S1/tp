package seedu.address.model.task.comparator;

import java.util.Comparator;

import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;

/**
 * Comparator to compare two Tasks based on their name.
 */
public class TaskComparatorByName implements Comparator<Task> {
    /**
     * Compare two Task based on their name, lexicographically.
     *
     * @param task first task
     * @param otherTask second task
     * @return -1 if task's name is lexicographically less than value of otherTask's name,
     *         0 if both task name are the same, and 1 if value of task's name is lexicographically
     *         greater than value of otherTask's name.
     */
    @Override
    public int compare(Task task, Task otherTask) {
        TaskName description = task.getName();
        TaskName otherName = otherTask.getName();
        TaskNameComparator descriptionComparator = new TaskNameComparator();
        return descriptionComparator.compare(description, otherName);
    }
}
