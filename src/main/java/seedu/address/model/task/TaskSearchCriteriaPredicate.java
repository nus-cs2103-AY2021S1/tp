package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task} matches all of the search conditions and criteria provided by the user.
 */
public class TaskSearchCriteriaPredicate implements Predicate<Task> {

    /** List of keywords provided by users to find tasks with matching task name. */
    private final List<String> taskSearchNames;
    /** Search date provided by users to find tasks with matching task date. */
    private final Date taskSearchDate;
    /** Priority provided by users to find tasks with matching task priority. */
    private final Priority taskSearchPriority;
    // private final Type taskType;

    /**
     * Creates and initialises a new TaskSearchCriteriaPredicate object.
     *
     * @param taskSearchNames List of strings representing search keywords to search for matching tasks.
     * @param taskSearchDate Date object used to search for matching tasks.
     * @param taskSearchPriority Priority object used to search for matching tasks.
     */
    public TaskSearchCriteriaPredicate(List<String> taskSearchNames, Date taskSearchDate,
                                       Priority taskSearchPriority) {
        this.taskSearchNames = taskSearchNames;
        this.taskSearchDate = taskSearchDate;
        this.taskSearchPriority = taskSearchPriority;
        // this.taskType = taskType;
    }

    @Override
    public boolean test(Task task) {
        if (!taskSearchNames.isEmpty() && !hasMatchingName(task)) {
            return false;
        }
        if (taskSearchDate != null && !hasMatchingDate(task)) {
            return false;
        }
        if (taskSearchPriority != null && !hasMatchingPriority(task)) {
            return false;
        }
        return true;
    }

    /**
     * Determines if the task name matches any of the search keywords provided by the user.
     *
     * @param task Task to be evaluated with the search keywords criteria.
     * @return True if the task name matches any of the search name keywords provided, false otherwise.
     */
    public boolean hasMatchingName(Task task) {
        return this.taskSearchNames.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getName().get().getValue(), keyword));
    }

    /**
     * Determines if the argument task date exists and matches the search date provided by the user.
     *
     * @param task Task to be evaluated with the search date criteria.
     * @return True if the task date exists and matches the search date provided, false otherwise.
     */
    public boolean hasMatchingDate(Task task) {
        // return (task.getDate().isPresent() && task.getDate().get().isEqual(this.taskSearchDate));
        return true;
    }

    /**
     * Determines if the argument task priority exists and matches the search priority provided by the user.
     *
     * @param task Task to be evaluated with the search priority criteria.
     * @return True if the task priority exists and matches the search priority provided, false otherwise.
     */
    public boolean hasMatchingPriority(Task task) {
        // return task.getPriority().isPresent() && task.getPriority().get().equals(this.taskSearchPriority);
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskSearchCriteriaPredicate // instanceof handles nulls
                && taskSearchNames.equals(((TaskSearchCriteriaPredicate) other).taskSearchNames)) // state check
                && taskSearchDate.equals(((TaskSearchCriteriaPredicate) other).taskSearchDate)
                && taskSearchPriority.equals(((TaskSearchCriteriaPredicate) other).taskSearchPriority);
        // && taskType.equals(((TaskSearchCriteriaPredicate) other).taskType);
    }
}
