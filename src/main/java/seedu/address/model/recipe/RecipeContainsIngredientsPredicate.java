package seedu.address.model.recipe;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code ingredients} matches any of the keywords given.
 */
public class RecipeContainsIngredientsPredicate extends RecipeContainsKeywordsPredicate
        implements Predicate<Recipe> {

    public RecipeContainsIngredientsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Recipe recipe) {
        String ingredients = recipe.getIngredient().stream().map(Object::toString).collect(Collectors.joining(","));
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(ingredients, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
