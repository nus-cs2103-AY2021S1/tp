package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class TaskTypeMatchesKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TaskTypeMatchesKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getType(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskTypeMatchesKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TaskTypeMatchesKeywordsPredicate) other).keywords)); // state check
    }

}
