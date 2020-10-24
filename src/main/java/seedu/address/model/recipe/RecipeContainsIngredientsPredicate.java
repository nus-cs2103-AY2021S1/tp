package seedu.address.model.recipe;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code ingredients} matches any of the keywords given.
 */
public class RecipeContainsIngredientsPredicate implements RecipeContainsKeywordsPredicate {

    protected final List<String> keywords;

    public RecipeContainsIngredientsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }
    @Override
    public boolean test(Recipe recipe) {
        String ingredients = recipe.getIngredient().stream().map(Object::toString).collect(Collectors.joining(","));

        if (keywords.isEmpty()) {
            return false;
        }

        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(ingredients, keyword));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecipeContainsIngredientsPredicate // instanceof handles nulls
                && keywords.equals(((RecipeContainsIngredientsPredicate) other).keywords)); // state check
    }

}
