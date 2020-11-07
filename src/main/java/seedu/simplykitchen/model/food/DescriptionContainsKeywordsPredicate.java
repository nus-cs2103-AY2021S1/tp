package seedu.simplykitchen.model.food;

import java.util.List;
import java.util.function.Predicate;

import seedu.simplykitchen.commons.util.StringUtil;

/**
 * Tests that a {@code Food}'s {@code Description} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Food> {
    private final List<String> keywords;

    /**
     * @param keywords to search for in description of food items.
     */
    public DescriptionContainsKeywordsPredicate(List<String> keywords) {
        assert keywords != null : "Keywords cannot be null.";
        this.keywords = keywords;
    }

    @Override
    public boolean test(Food food) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(food
                        .getDescription().fullDescription, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
