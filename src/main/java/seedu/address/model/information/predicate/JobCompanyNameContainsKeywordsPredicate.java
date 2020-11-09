package seedu.address.model.information.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.information.Job;

/**
 * Tests that a {@code Job}'s {@code Name} matches any of the keywords given.
 */
public class JobCompanyNameContainsKeywordsPredicate implements Predicate<Job> {
    private final List<String> keywords;

    public JobCompanyNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Job job) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsPhraseIgnoreCase(
                        job.getCompanyName().fullCompanyName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobCompanyNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((JobCompanyNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
