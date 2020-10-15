package seedu.address.model.task.comparator;

import java.util.Comparator;

import seedu.address.model.task.Date;
import seedu.address.model.task.Task;

/**
 * Comparator to compare two Tasks based on their dates.
 */
public class TaskComparatorByDate implements Comparator<Task> {
    /**
     * Compares two tasks based on date.
     *
     * @param task first task
     * @param otherTask second task
     * @return 1 if task has an earlier date, 0 if both tasks have the same date,
     *         and -1 if otherTask has an earlier date
     */
    @Override
    public int compare(Task task, Task otherTask) {
        Date date = task.getDate();
        Date otherDate = otherTask.getDate();
        DateComparator descriptionComparator = new DateComparator();
        return descriptionComparator.compare(date, otherDate);
    }
}
