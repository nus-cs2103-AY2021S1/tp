package seedu.address.model.ingredient;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code Name} matches any of the keywords given.
 */
public class IngredientContainsKeywordsPredicate implements Predicate<Ingredient> {
    private final List<String> keywords;

    public IngredientContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Ingredient ingredient) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(ingredient.getValue(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IngredientContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((IngredientContainsKeywordsPredicate) other).keywords)); // state check
    }

}
