package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ExpenseBuilder;

public class DateContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DateContainsKeywordsPredicate firstPredicate =
                new DateContainsKeywordsPredicate(firstPredicateKeywordList);
        DateContainsKeywordsPredicate secondPredicate =
                new DateContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DateContainsKeywordsPredicate firstPredicateCopy =
                new DateContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_dateContainsKeywords_returnsTrue() {
        // One keyword (case sensitive)
        DateContainsKeywordsPredicate predicate =
                new DateContainsKeywordsPredicate(Collections.singletonList(new Date("0").getDate()));
        assertTrue(predicate.test(new ExpenseBuilder().withDate("0").build()));
    }

    @Test
    public void test_dateDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DateContainsKeywordsPredicate predicate =
                new DateContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ExpenseBuilder().withDate("1").build()));

        // Non-matching keyword
        predicate = new DateContainsKeywordsPredicate(Arrays.asList(new Date("0").getDate()));
        assertFalse(predicate.test(new ExpenseBuilder().withDate("1").build()));

        // Keywords match desc and category but does not match date
        predicate = new DateContainsKeywordsPredicate(Arrays.asList("mcd", "Food"));
        assertFalse(predicate.test(new ExpenseBuilder().withCategory("Food").withDescription("mcd")
                .withDate("1").build()));
    }
}
