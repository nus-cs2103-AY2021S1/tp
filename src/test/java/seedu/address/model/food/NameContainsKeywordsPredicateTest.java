package seedu.address.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MenuItemBuilder;


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
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Prata"));
        assertTrue(predicate.test(new MenuItemBuilder().withName("Cheese Prata").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Cheese", "Prata"));
        assertTrue(predicate.test(new MenuItemBuilder().withName("Cheese Prata").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Egg", "Prata"));
        assertTrue(predicate.test(new MenuItemBuilder().withName("Egg Prata").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("cHeeSe", "pRAtA"));
        assertTrue(predicate.test(new MenuItemBuilder().withName("Cheese Prata").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MenuItemBuilder().withName("Prata").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Milo"));
        assertFalse(predicate.test(new MenuItemBuilder().withName("Cheese Prata").build()));

        // Keywords match price, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("2.5"));
        assertFalse(predicate.test(new MenuItemBuilder().withName("Prata").withPrice(2.5).build()
                ));
    }
}
