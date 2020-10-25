package seedu.address.model.vendor;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.food.Food;

/**
 * Tests that a {@code Vendor}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Food> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Food food) {
        StringBuilder sentence = new StringBuilder(food.getName() + ' ');
        food.getTags().forEach(x -> sentence.append(x.tagName).append(' '));
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(sentence.toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
