package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.IngredientBuilder;

public class IngredientContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        IngredientContainsKeywordsPredicate firstPredicate =
                new IngredientContainsKeywordsPredicate(firstPredicateKeywordList);
        IngredientContainsKeywordsPredicate secondPredicate =
                new IngredientContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IngredientContainsKeywordsPredicate firstPredicateCopy =
                new IngredientContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different ingredient -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_ingredientContainsKeywords_returnsTrue() {
        // One keyword
        IngredientContainsKeywordsPredicate predicate =
                new IngredientContainsKeywordsPredicate(Collections.singletonList("Sandwich"));
        assertTrue(predicate.test(new IngredientBuilder().withValue("Sandwich Bob").build()));

        // Multiple keywords
        predicate = new IngredientContainsKeywordsPredicate(Arrays.asList("Sandwich", "Bob"));
        assertTrue(predicate.test(new IngredientBuilder().withValue("Sandwich Bob").build()));

        // Only one matching keyword
        predicate = new IngredientContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new IngredientBuilder().withValue("Sandwich Carol").build()));
        // Mixed-case keywords
        predicate = new IngredientContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new IngredientBuilder().withValue("Sandwich Bob").build()));
    }

    @Test
    public void test_ingredientDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        IngredientContainsKeywordsPredicate predicate =
                new IngredientContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new IngredientBuilder().withValue("Alice").build()));

        // Non-matching keyword
        predicate = new IngredientContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new IngredientBuilder().withValue("Alice Bob").build()));

        // Keywords match ingredients, email and address, but does not match name
        predicate = new IngredientContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new IngredientBuilder().withValue("Alice").build()));
    }
}
