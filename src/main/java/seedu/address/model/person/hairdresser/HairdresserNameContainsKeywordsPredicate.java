/* @@author wayneswq */
package seedu.address.model.person.hairdresser;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Hairdresser}'s {@code Name} matches any of the keywords given.
 */
public class HairdresserNameContainsKeywordsPredicate implements Predicate<Hairdresser> {
    private final List<String> keywords;

    public HairdresserNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Hairdresser hairdresser) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(hairdresser.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HairdresserNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((HairdresserNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
