package seedu.address.model.recipe;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code tags} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements RecipeContainsKeywordsPredicate {

    protected final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Recipe recipe) {
        String tags = recipe.getTags().stream().map(Object::toString).collect(Collectors.joining(","));
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tags, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
