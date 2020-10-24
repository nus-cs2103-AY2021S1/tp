package chopchop.model.attributes;

import chopchop.commons.util.StringUtil;
import chopchop.model.recipe.Recipe;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Tests that an item's {@code Name} matches any of the keywords given.
 */
public class IngredientsContainsKeywordsPredicate implements Predicate<Recipe> {
    private final List<String> keywords;

    public IngredientsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Recipe recipe) {
        Stream<String> indNameList = recipe.getIngredients()
                .stream().map(indRef -> indRef.getName());

        // Returns recipes whose ingredient name list containing any of these keywords
        /*return indNameList.anyMatch(indName -> this.keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(indName, keyword)));*/

        // Returns recipes whose ingredient name list contains all these keywords
        return this.keywords.stream().allMatch(keyword -> recipe.getIngredients()
                .stream().map(indRef -> indRef.getName())
                .anyMatch(indName -> StringUtil.containsWordIgnoreCase(indName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IngredientsContainsKeywordsPredicate // instanceof handles nulls
                && this.keywords.equals(((IngredientsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
