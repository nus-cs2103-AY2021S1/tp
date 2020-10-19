package seedu.address.model.task;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordPredicate implements Predicate<Task> {

    private final List<String> keywords;

    public NameContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getName().get().getValue(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordPredicate) other).keywords)); // state check
    }
}
