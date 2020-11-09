package seedu.address.model.information.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.information.Job;

/**
 * Tests that a {@code Job}'s {@code Priority} matches all of the keywords given.
 */
public class JobPriorityContainsKeywordsPredicate implements Predicate<Job> {
    private final List<String> keywords;

    public JobPriorityContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Job job) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsPhraseIgnoreCase(job.getPriority().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobPriorityContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((JobPriorityContainsKeywordsPredicate) other).keywords)); // state check
    }

}
