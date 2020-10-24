package seedu.address.model.task.comparator;

import java.util.Comparator;
import java.util.Optional;

import seedu.address.model.task.Priority;
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
     *         If one or more of the task does not have a priority, return -1 if only the second task
     *         does not have a priority, 0 if both task do not have a priority, and 1 if only the first task
     *         does not have a priority.
     */
    @Override
    public int compare(Task task, Task otherTask) {
        Optional<Priority> priority = task.getPriority();
        Optional<Priority> otherPriority = otherTask.getPriority();

        if (priority.isPresent() && otherPriority.isPresent()) {
            return priority.get().compareTo(otherPriority.get());
        }
        if (priority.isPresent()) {
            return -1;
        }
        if (otherPriority.isPresent()) {
            return 1;
        }
        return 0;
    }

}
