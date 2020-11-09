package seedu.address.model.information.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.information.Job;

/**
 * Tests that a {@code Job}'s {@code Vacancy} matches all of the keywords given.
 */
public class JobVacancyContainsKeywordsPredicate implements Predicate<Job> {
    private final List<String> keywords;

    public JobVacancyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Job job) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(job.getVacancy().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobVacancyContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((JobVacancyContainsKeywordsPredicate) other).keywords)); // state check
    }

}
