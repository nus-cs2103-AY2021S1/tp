package seedu.address.model.recipe;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Ingredient}'s {@code Name} matches any of the keywords given.
 */
public class KeywordsContainIngredientPredicate implements Predicate<Ingredient> {
    private final List<String> keywords;

    public KeywordsContainIngredientPredicate(List<String> keywords) {
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
                || (other instanceof KeywordsContainIngredientPredicate // instanceof handles nulls
                && keywords.equals(((KeywordsContainIngredientPredicate) other).keywords)); // state check
    }

}
