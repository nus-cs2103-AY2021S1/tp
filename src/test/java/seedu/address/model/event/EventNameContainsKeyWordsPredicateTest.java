package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.event.EventBuilder;

public class EventNameContainsKeyWordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("second");
        List<String> secondPredicateKeywordList = Arrays.asList("second", "third");

        EventNameContainsKeyWordsPredicate firstPredicate =
                new EventNameContainsKeyWordsPredicate(firstPredicateKeywordList);
        EventNameContainsKeyWordsPredicate secondPredicate =
                new EventNameContainsKeyWordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EventNameContainsKeyWordsPredicate firstPredicateCopy =
                new EventNameContainsKeyWordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(8));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_eventNameContainsKeywords_returnsTrue() {
        // One keyword
        EventNameContainsKeyWordsPredicate predicate =
                new EventNameContainsKeyWordsPredicate(Collections.singletonList("Test"));
        assertTrue(predicate.test(new EventBuilder().withName("Homework test").build()));

        // Multiple keywords
        predicate = new EventNameContainsKeyWordsPredicate(Arrays.asList("Homework", "test"));
        assertTrue(predicate.test(new EventBuilder().withName("Homework test").build()));

        // Only one matching keyword
        predicate = new EventNameContainsKeyWordsPredicate(Arrays.asList("Homework", "test"));
        assertTrue(predicate.test(new EventBuilder().withName("Homework assignment").build()));

    }

    @Test
    public void test_eventNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EventNameContainsKeyWordsPredicate predicate = new EventNameContainsKeyWordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EventBuilder().withName("test").build()));

        // Non-matching keyword
        predicate = new EventNameContainsKeyWordsPredicate(Arrays.asList("test"));
        assertFalse(predicate.test(new EventBuilder().withName("homework").build()));
    }
}
