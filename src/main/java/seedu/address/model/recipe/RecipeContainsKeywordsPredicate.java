package seedu.address.model.recipe;

import java.util.function.Predicate;

/**
 * Tests that a {@code Recipe}'s {@code attributes} matches any of the keywords given.
 */
public interface RecipeContainsKeywordsPredicate extends Predicate<Recipe> {

    @Override
    public boolean test(Recipe recipe);

    @Override
    public boolean equals(Object other);
}
