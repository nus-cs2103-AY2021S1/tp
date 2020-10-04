package chopchop.model.attributes;

import java.util.List;
import java.util.function.Predicate;

import chopchop.commons.util.StringUtil;
import chopchop.model.FoodEntry;

/**
 * Tests that a {@code FoodEntry}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate <F extends FoodEntry> implements Predicate<F> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(F fe) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(fe.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
