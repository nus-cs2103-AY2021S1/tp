package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RecipeBuilder;

public class RecommendPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        RecommendPredicate firstPredicate =
                new RecommendPredicate(firstPredicateKeywordList);
        RecommendPredicate secondPredicate =
                new RecommendPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RecommendPredicate firstPredicateCopy =
                new RecommendPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different recipe -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_recipesContainsIngredients_returnsTrue() {
        // ingredient match without quantity
        RecommendPredicate predicate =
                new RecommendPredicate(Collections.singletonList("Bread"));
        assertTrue(predicate.test(new RecipeBuilder().withIngredient("Bread", "").build()));

        // ingredient match with quantity
        predicate = new RecommendPredicate(Arrays.asList("Bread"));
        assertTrue(predicate.test(new RecipeBuilder().withIngredient("Bread", "2 whole").build()));

        // Mixed-case ingredient keywords
        predicate = new RecommendPredicate(Arrays.asList("BrEad"));
        assertTrue(predicate.test(new RecipeBuilder().withIngredient("Bread", "2 whole").build()));
    }

    @Test
    public void test_recipeDoesNotContainIngredients_returnsFalse() {
        // Zero ingredient keywords
        RecommendPredicate predicate = new RecommendPredicate(Collections.emptyList());
        assertFalse(predicate.test(new RecipeBuilder().withIngredient("Potato", "").build()));

        // Only one matching ingredient keyword
        predicate = new RecommendPredicate(Arrays.asList("Potato", "Bread"));
        assertFalse(predicate.test(new RecipeBuilder().withIngredient("Potato Bread", "").build()));

        // Non-matching ingredient keyword
        predicate = new RecommendPredicate(Arrays.asList("Grape"));
        assertFalse(predicate.test(new RecipeBuilder().withIngredient("Potato Bread", "").build()));

        // Keywords match name and calories, but does not match ingredient
        predicate = new RecommendPredicate(
                Arrays.asList("Sandwich", "70"));
        assertFalse(predicate.test(new RecipeBuilder()
                .withName("Sandwich")
                .withCalories(70)
                .build()));
    }
}
