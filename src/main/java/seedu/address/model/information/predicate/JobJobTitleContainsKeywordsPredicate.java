package seedu.address.model.information.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.information.Job;

/**
 * Tests that a {@code Job}'s {@code Job Title} matches all of the keywords given.
 */
public class JobJobTitleContainsKeywordsPredicate implements Predicate<Job> {
    private final List<String> keywords;

    public JobJobTitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Job job) {
        return keywords.stream()
                // Job Title contains keyword
                .allMatch(keyword -> StringUtil.containsPhraseIgnoreCase(job.getJobTitle().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobJobTitleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((JobJobTitleContainsKeywordsPredicate) other).keywords)); // state check
    }

}
