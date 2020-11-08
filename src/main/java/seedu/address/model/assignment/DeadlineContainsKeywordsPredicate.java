package seedu.address.model.assignment;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that an {@code Assignment}'s deadline matches any of the keywords given.
 */
public class DeadlineContainsKeywordsPredicate implements Predicate<Assignment> {
    private final List<String> keywords;

    public DeadlineContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Assignment assignment) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil
                        .containsWordIgnoreCase(assignment.getDeadline().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DeadlineContainsKeywordsPredicate) other).keywords)); // state check
    }

}
