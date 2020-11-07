package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Status} matches the search status provided by the user.
 */
public class TaskMatchesStatusPredicate implements Predicate<Task> {

    private final Status searchStatus;

    /**
     * Creates and initialises a new TaskMatchesStatusPredicate object.
     *
     * @param searchStatus Status provided by the user to search for matching tasks.
     */
    public TaskMatchesStatusPredicate(Status searchStatus) {
        requireNonNull(searchStatus);
        this.searchStatus = searchStatus;
    }

    @Override
    public boolean test(Task task) {
        requireNonNull(task);
        return task.hasSameStatus(searchStatus);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskMatchesStatusPredicate // instanceof handles nulls
                && searchStatus.equals(((TaskMatchesStatusPredicate) other).searchStatus)); // state check
    }
}
