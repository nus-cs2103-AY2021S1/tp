package seedu.address.model.information.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.information.Job;

/**
 * Tests that any of a {@code Job}'s {@code Tags} matches all of the keywords given.
 */
public class JobTagsContainKeywordsPredicate implements Predicate<Job> {
    private final List<String> keywords;

    public JobTagsContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Job job) {
        return keywords.stream()
                // Any tags contain keyword
                .allMatch(keyword -> job.getTags().stream()
                        .anyMatch(tag -> StringUtil.containsPhraseIgnoreCase(tag.toString(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobTagsContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((JobTagsContainKeywordsPredicate) other).keywords)); // state check
    }

}
