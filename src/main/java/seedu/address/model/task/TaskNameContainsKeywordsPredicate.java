package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code TaskName} matches any of the search keywords provided by the user.
 */
public class TaskNameContainsKeywordsPredicate implements Predicate<Task> {

    /** List of keywords to test for matching tasks. */
    private final List<String> keywords;

    /**
     * Creates and initialises a TaskNameContainsKeywordsPredicate object to test for matching tasks.
     *
     * @param keywords List of keywords provided by the user.
     */
    public TaskNameContainsKeywordsPredicate(List<String> keywords) {
        assert !keywords.isEmpty() : "At least one search keyword must be present";
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        requireNonNull(task);
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getName().get().getValue(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TaskNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
