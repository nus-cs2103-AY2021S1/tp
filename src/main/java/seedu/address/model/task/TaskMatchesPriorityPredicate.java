package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Priority} matches the search priority provided by the user.
 */
public class TaskMatchesPriorityPredicate implements Predicate<Task> {

    /** Priority object to test for matching tasks. */
    private final Priority searchPriority;

    /**
     * Creates and initialises a TaskMatchesPriorityPredicate object to test for matching tasks.
     *
     * @param searchPriority Priority provided by users to find matching tasks.
     */
    public TaskMatchesPriorityPredicate(Priority searchPriority) {
        requireNonNull(searchPriority);
        this.searchPriority = searchPriority;
    }

    @Override
    public boolean test(Task task) {
        requireNonNull(task);
        boolean TaskPriorityIsPresent = task.getPriority().isPresent();
        if (TaskPriorityIsPresent) {
            return task.hasSamePriority(searchPriority);
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskMatchesPriorityPredicate // instanceof handles nulls
                && searchPriority.equals(((TaskMatchesPriorityPredicate) other).searchPriority)); // state check
    }

}
