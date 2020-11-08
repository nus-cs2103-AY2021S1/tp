package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Task}'s {@code Set<Tag>} matches any of the search tags provided by the user.
 */
public class TaskContainsTagsPredicate implements Predicate<Task> {

    /** Tags provided by the user to search for tasks containing at least one matching tag. */
    private final Set<Tag> tags;

    /**
     * Creates and initialises a new TaskContainsTagsPredicate object to test for matching tasks.
     *
     * @param tags Set of search tags provided by the user.
     */
    public TaskContainsTagsPredicate(Set<Tag> tags) {
        assert !tags.isEmpty() : "At least one search tag must be present";
        this.tags = tags;
    }

    @Override
    public boolean test(Task task) {
        requireNonNull(task);
        boolean TaskTagIsPresent = task.getTags().isPresent();
        if (TaskTagIsPresent) {
            Set<Tag> taskTags = task.getTags().get();
            return tags.stream()
                    .anyMatch(tag -> taskTags.contains(tag));
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskContainsTagsPredicate // instanceof handles nulls
                && tags.equals(((TaskContainsTagsPredicate) other).tags)); // state check
    }
}
