package seedu.address.model.task.comparator;

import java.util.Comparator;
import java.util.Optional;

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
     *         and -1 if otherTask has an earlier date. If one or more of the task
     *         does not have a date, return -1 if only the second task does not have
     *         a date, 0 if both task do not have a date, and 1 if only the first task
     *         does not have a date.
     */
    @Override
    public int compare(Task task, Task otherTask) {
        Optional<Date> date = task.getDate();
        Optional<Date> otherDate = otherTask.getDate();
        DateComparator dateComparator = new DateComparator();

        if (date.isPresent() && otherDate.isPresent()) {
            return dateComparator.compare(date.get(), otherDate.get());
        }
        if (date.isPresent()) {
            return -1;
        }
        if (otherDate.isPresent()) {
            return 1;
        }
        return 0;
    }

}
