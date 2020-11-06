package seedu.address.model.consumption;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalRecipes.SANDWICH;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ConsumptionBuilder;

public class ConsumptionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ConsumptionContainsKeywordsPredicate firstPredicate =
                new ConsumptionContainsKeywordsPredicate(firstPredicateKeywordList);
        ConsumptionContainsKeywordsPredicate secondPredicate =
                new ConsumptionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ConsumptionContainsKeywordsPredicate firstPredicateCopy =
                new ConsumptionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different ingredient -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void consumptionContainsKeywords_returnsTrue() {
        // One keyword
        ConsumptionContainsKeywordsPredicate predicate =
                new ConsumptionContainsKeywordsPredicate(Collections.singletonList("Sandwich"));
        assertTrue(predicate.test(new ConsumptionBuilder().withRecipe(SANDWICH).build()));

        // Mixed-case keywords
        predicate = new ConsumptionContainsKeywordsPredicate(Arrays.asList("saNdWicH"));
        assertTrue(predicate.test(new ConsumptionBuilder().withRecipe(SANDWICH).build()));
    }

    @Test
    public void test_ingredientDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ConsumptionContainsKeywordsPredicate predicate =
                new ConsumptionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ConsumptionBuilder().withRecipe(SANDWICH).build()));

        // Non-matching keyword
        predicate = new ConsumptionContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new ConsumptionBuilder().withRecipe(SANDWICH).build()));
    }
}
