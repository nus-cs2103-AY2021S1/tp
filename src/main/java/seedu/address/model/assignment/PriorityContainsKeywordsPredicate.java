package seedu.address.model.assignment;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that an {@code Assignment}'s {@code Priority} matches any of the keywords given.
 */
public class PriorityContainsKeywordsPredicate implements Predicate<Assignment> {
    private final List<String> keywords;

    public PriorityContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Assignment assignment) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(assignment.getPriority().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriorityContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PriorityContainsKeywordsPredicate) other).keywords)); // state check
    }

}
