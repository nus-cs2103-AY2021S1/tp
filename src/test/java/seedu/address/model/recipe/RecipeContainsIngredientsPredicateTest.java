package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RecipeBuilder;

public class RecipeContainsIngredientsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        RecipeContainsIngredientsPredicate firstPredicate =
                new RecipeContainsIngredientsPredicate(firstPredicateKeywordList);
        RecipeContainsIngredientsPredicate secondPredicate =
                new RecipeContainsIngredientsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RecipeContainsIngredientsPredicate firstPredicateCopy =
                new RecipeContainsIngredientsPredicate(firstPredicateKeywordList);
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
        // One ingredient keyword
        RecipeContainsIngredientsPredicate predicate =
                new RecipeContainsIngredientsPredicate(Collections.singletonList("Bread"));
        assertTrue(predicate.test(new RecipeBuilder().withIngredient("White Bread", "").build()));

        // Multiple ingredient keywords
        predicate = new RecipeContainsIngredientsPredicate(Arrays.asList("Potato", "Bread"));
        assertTrue(predicate.test(new RecipeBuilder().withIngredient("Potato Bread", "").build()));

        // Mixed-case ingredient keywords
        predicate = new RecipeContainsIngredientsPredicate(Arrays.asList("PoTaTo", "BRead"));
        assertTrue(predicate.test(new RecipeBuilder().withIngredient("Potato Bread", "").build()));
    }

    @Test
    public void test_recipeDoesNotContainIngredients_returnsFalse() {
        // Zero ingredient keywords
        RecipeContainsIngredientsPredicate predicate = new RecipeContainsIngredientsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new RecipeBuilder().withIngredient("Potato", "").build()));

        // Only one matching ingredient keyword
        predicate = new RecipeContainsIngredientsPredicate(Arrays.asList("Potato", "Grape"));
        assertFalse(predicate.test(new RecipeBuilder().withIngredient("Potato Bread", "").build()));

        // Non-matching ingredient keyword
        predicate = new RecipeContainsIngredientsPredicate(Arrays.asList("Grape"));
        assertFalse(predicate.test(new RecipeBuilder().withIngredient("Potato Bread", "").build()));

        // Keywords match name and calories, but does not match ingredient
        predicate = new RecipeContainsIngredientsPredicate(
                Arrays.asList("Sandwich", "70"));
        assertFalse(predicate.test(new RecipeBuilder()
                .withName("Sandwich")
                .withCalories(70)
                .build()));
    }
}
