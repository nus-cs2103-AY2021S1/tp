package seedu.address.model.task.comparator;

import java.util.Comparator;

import seedu.address.model.task.Task;

/**
 * Comparator to compare two Tasks based on their priority.
 */
public class TaskComparatorByPriority implements Comparator<Task> {
    /**
     * Compares two task based on their priority.
     *
     * @param task first task
     * @param otherTask second task
     * @return -1 if first task has lower priority level than otherTask, 0 if both task have same
     *         priority level, and 1 if first task has higher priority level than otherTask
     */
    @Override
    public int compare(Task task, Task otherTask) {
        return task.getPriority().compareTo(otherTask.getPriority());
    }
}
