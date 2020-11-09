package seedu.address.model.information.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.information.Job;

/**
 * Tests that a {@code Job}'s {@code Email} matches all of the keywords given.
 */
public class JobEmailContainsKeywordsPredicate implements Predicate<Job> {
    private final List<String> keywords;

    public JobEmailContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Job job) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsPhraseIgnoreCase(job.getEmail().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobEmailContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((JobEmailContainsKeywordsPredicate) other).keywords)); // state check
    }

}
