package seedu.address.model.task;

import java.util.Comparator;

/**
 * A utility class to store various comparators of Task
 */
public class TaskComparators {
    public static final Comparator<Task> SORT_BY_DEADLINE = Comparator.naturalOrder();
    public static final Comparator<Task> SORT_BY_TASK_NAME = Comparator.comparing(Task::getTaskName);
    public static final Comparator<Task> SORT_BY_PROGRESS = Comparator.comparing(Task::getProgress);
    public static final Comparator<Task> SORT_BY_IS_DONE = Comparator.comparing(Task::isDone);
}
