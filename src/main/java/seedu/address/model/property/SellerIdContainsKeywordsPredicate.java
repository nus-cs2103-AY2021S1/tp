package seedu.address.model.property;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Property}'s {@code SellerId} matches any of the keywords given.
 */
public class SellerIdContainsKeywordsPredicate implements Predicate<Property> {
    private final List<String> keywords;

    public SellerIdContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Property property) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(property.getSellerId().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SellerIdContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SellerIdContainsKeywordsPredicate) other).keywords)); // state check
    }

}
