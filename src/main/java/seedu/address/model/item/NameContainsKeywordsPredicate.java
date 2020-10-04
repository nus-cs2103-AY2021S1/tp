package seedu.address.model.item;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Item}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Item> {

    private final List<String> keywords;
    private final String type;

    public NameContainsKeywordsPredicate(List<String> keywords, String type) {
        this.keywords = keywords;
        this.type = type;
    }

    @Override
    public boolean test(Item item) {
        switch(type) {
        case "Name":
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(item.getName().fullName, keyword));
        case "Supplier":
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(item.getSupplier().value, keyword));
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
