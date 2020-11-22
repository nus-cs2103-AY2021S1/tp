package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ExpenseBuilder;

public class CategoryContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CategoryContainsKeywordsPredicate firstPredicate =
                new CategoryContainsKeywordsPredicate(firstPredicateKeywordList);
        CategoryContainsKeywordsPredicate secondPredicate =
                new CategoryContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CategoryContainsKeywordsPredicate firstPredicateCopy =
                new CategoryContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_categoryContainsKeywords_returnsTrue() {
        // One keyword
        CategoryContainsKeywordsPredicate predicate =
                new CategoryContainsKeywordsPredicate(Collections.singletonList("Food"));
        assertTrue(predicate.test(new ExpenseBuilder().withCategory("Food drink").build()));

        // Multiple keywords
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("Food", "drink"));
        assertTrue(predicate.test(new ExpenseBuilder().withCategory("Food drink").build()));

        // Only one matching keyword
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("Food", "drink"));
        assertTrue(predicate.test(new ExpenseBuilder().withCategory("Food drinks").build()));

        // Mixed-case keywords
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("FoOd", "drINk"));
        assertTrue(predicate.test(new ExpenseBuilder().withCategory("Food drink").build()));
    }

    @Test
    public void test_categoryDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CategoryContainsKeywordsPredicate predicate =
                new CategoryContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ExpenseBuilder().withCategory("Food").build()));

        // Non-matching keyword
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("Food"));
        assertFalse(predicate.test(new ExpenseBuilder().withCategory("Entertainment").build()));

        // Keywords match desc but does not match category
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("mcd"));
        assertFalse(predicate.test(new ExpenseBuilder().withCategory("Food").withDescription("mcd").build()));
    }
}
