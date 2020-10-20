package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RecipeBuilder;

public class TagContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different recipe -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.singletonList("healthy"));
        assertTrue(predicate.test(new RecipeBuilder().withTags("super healthy").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("healthy", "low calories"));
        assertTrue(predicate.test(new RecipeBuilder().withTags("low calories healthy").build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("healthy", "high calories"));
        assertTrue(predicate.test(new RecipeBuilder().withTags("low calories healthy").build()));
        // Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("heAltHy", "lOw calOrieS"));
        assertTrue(predicate.test(new RecipeBuilder().withTags("low calories healthy").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new RecipeBuilder().withTags("Sandwich").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Pork"));
        assertFalse(predicate.test(new RecipeBuilder().withTags("Sandwich Pasta").build()));

        // Keywords match name, ingredients, calories but does not match tags
        predicate = new TagContainsKeywordsPredicate(
                Arrays.asList("Sandwich", "Kaiser", "Rolls", "Or", "Other", "Bread", "2", "whole", "70"));
        assertFalse(predicate.test(new RecipeBuilder()
                .withName("Sandwich")
                .withIngredient("Kaiser Rolls Or Other Bread", "2 whole")
                .withCalories(70)
                .withTags("healthy")
                .build()));
    }
}
