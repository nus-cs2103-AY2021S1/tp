package seedu.address.model.task;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Task} matches all of the search conditions and criteria provided by the user.
 */
public class TaskSearchCriteriaPredicate implements Predicate<Task> {

    private final List<String> taskNameKeywords;
    private final Date taskDate;
    private final Priority taskPriority;
    // private final Type taskType;

    public TaskSearchCriteriaPredicate(List<String> taskNameKeywords, Date taskDate,
                                       Priority taskPriority) {
        this.taskNameKeywords = taskNameKeywords;
        this.taskDate = taskDate;
        this.taskPriority = taskPriority;
        // this.taskType = taskType;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getName().get().getValue(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskSearchCriteriaPredicate // instanceof handles nulls
                && taskNameKeywords.equals(((TaskSearchCriteriaPredicate) other).taskNameKeywords)) // state check
                && taskDate.equals(((TaskSearchCriteriaPredicate) other).taskNameKeywords)
                && taskPriority.equals(((TaskSearchCriteriaPredicate) other).taskPriority);
                // && taskType.equals(((TaskSearchCriteriaPredicate) other).taskType);
    }
}
