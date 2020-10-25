package seedu.pivot.model.investigationcase;

import java.util.List;
import java.util.function.Predicate;

import seedu.pivot.commons.util.StringUtil;

/**
 * Tests that a {@code Case}'s {@code Title} matches any of the keywords given.
 */
public class DetailsContainsKeywordsPredicate implements Predicate<Case> {
    private final List<String> keywords;

    public DetailsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Case investigationCase) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(investigationCase.getTitle().getAlphaNum(),
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetailsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DetailsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
