package seedu.jarvis.model.masterycheck;

import java.util.List;
import java.util.function.Predicate;

import seedu.jarvis.commons.util.StringUtil;

/**
 * Tests that a {@code MasteryCheck}'s student name matches any of the keywords given.
 */
public class MasteryCheckNameContainsKeywordsPredicate implements Predicate<MasteryCheck> {
    private final List<String> keywords;

    public MasteryCheckNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(MasteryCheck masteryCheck) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(masteryCheck.getStudentName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MasteryCheckNameContainsKeywordsPredicate
                && keywords.equals(((MasteryCheckNameContainsKeywordsPredicate) other).keywords));
    }
}
