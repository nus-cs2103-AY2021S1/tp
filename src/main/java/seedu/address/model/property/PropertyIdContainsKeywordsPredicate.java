package seedu.address.model.property;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.id.PropertyId;

/**
 * Tests that a {@code Property}'s {@code PropertyId} matches any of the keywords given.
 */
public class PropertyIdContainsKeywordsPredicate implements Predicate<Property> {
    private final List<String> keywords;

    public PropertyIdContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Property property) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(property.getPropertyId().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyIdContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PropertyIdContainsKeywordsPredicate) other).keywords)); // state check
    }

}
