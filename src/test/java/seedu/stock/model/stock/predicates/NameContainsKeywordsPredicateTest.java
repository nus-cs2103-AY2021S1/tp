package seedu.stock.model.stock.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.stock.testutil.StockBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Banana"));
        assertTrue(predicate.test(new StockBuilder().withName("Banana Bun").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Banana", "Bun"));
        assertTrue(predicate.test(new StockBuilder().withName("Banana Bun").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Banana", "Juice"));
        assertTrue(predicate.test(new StockBuilder().withName("Banana Bun").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("bAnAna", "buN"));
        assertTrue(predicate.test(new StockBuilder().withName("Banana Bun").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StockBuilder().withName("Banana").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Apple"));
        assertFalse(predicate.test(new StockBuilder().withName("Banana Bun").build()));

        // Keywords match serial number, source, quantity and location but does not match name
        predicate = new NameContainsKeywordsPredicate(
                Arrays.asList("12345", "Section B", "Fairprice", "Fairprice1"));
        assertFalse(predicate.test(new StockBuilder().withName("Banana").withSerialNumber("Fairprice1")
                .withSource("Fairprice").withQuantity("12345").withLocation("Section B").build()));
    }
}
