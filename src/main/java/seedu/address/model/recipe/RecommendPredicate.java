package seedu.address.model.recipe;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code ingredients} matches all of the ingredients in the user's fridge.
 */
public class RecommendPredicate extends RecipeContainsKeywordsPredicate
        implements Predicate<Recipe> {

    public RecommendPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Recipe recipe) {
        List<String> ingredients =
                recipe.getIngredient().stream().map(x -> x.getValue().replaceAll("\\s+", ""))
                        .collect(toList());
        String str = keywords.stream().map(Object::toString).map(x -> x.replaceAll("\\s+", ""))
                .collect(Collectors.joining(" "));

        if (keywords.isEmpty()) {
            return false;
        }

        return ingredients.stream()
                .allMatch(ingredient -> StringUtil.containsWordIgnoreCase(str, ingredient));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecommendPredicate // instanceof handles nulls
                && keywords.equals(((RecommendPredicate) other).keywords)); // state check
    }

}
