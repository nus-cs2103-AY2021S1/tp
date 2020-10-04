package seedu.address.model.consumption;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Consumption}'s {@code Name} matches any of the keywords given.
 */
public class ConsumptionContainsKeywordsPredicate implements Predicate<Consumption> {
    private final List<String> keywords;

    public ConsumptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Consumption consumption) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(consumption.getRecipe()
                        .getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ConsumptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ConsumptionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
