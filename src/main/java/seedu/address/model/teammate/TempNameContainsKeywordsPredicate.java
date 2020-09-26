package seedu.address.model.teammate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Project}'s {@code Name} matches any of the keywords given.
 */
public class TempNameContainsKeywordsPredicate implements Predicate<TempTeammate> {
    private final List<String> keywords;

    public TempNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(TempTeammate project) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(project.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TempNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TempNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
