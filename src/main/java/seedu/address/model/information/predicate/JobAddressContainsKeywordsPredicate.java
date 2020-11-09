package seedu.address.model.information.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.information.Job;

/**
 * Tests that a {@code Job}'s {@code Address} matches all of the keywords given.
 */
public class JobAddressContainsKeywordsPredicate implements Predicate<Job> {
    private final List<String> keywords;

    public JobAddressContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Job job) {
        return keywords.stream()
                // Address contains keyword
                .allMatch(keyword -> StringUtil.containsPhraseIgnoreCase(job.getAddress().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobAddressContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((JobAddressContainsKeywordsPredicate) other).keywords)); // state check
    }

}
