package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FlashcardBuilder;

public class PriorityContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        PriorityContainsKeywordsPredicate firstPredicate = new PriorityContainsKeywordsPredicate("first");
        PriorityContainsKeywordsPredicate secondPredicate = new PriorityContainsKeywordsPredicate("second");

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        PriorityContainsKeywordsPredicate firstPredicateCopy = new PriorityContainsKeywordsPredicate("first");
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different flashcard -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_priorityContainsKeywords_returnsTrue() {
        // One keyword
        PriorityContainsKeywordsPredicate predicate = new PriorityContainsKeywordsPredicate("low");
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("low").build()));
    }

    @Test
    public void test_priorityDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PriorityContainsKeywordsPredicate predicate = new PriorityContainsKeywordsPredicate("");
        assertFalse(predicate.test(new FlashcardBuilder().withPriority("low").build()));

        // Non-matching keyword
        predicate = new PriorityContainsKeywordsPredicate("medium");
        assertFalse(predicate.test(new FlashcardBuilder().withPriority("low").build()));

        // Keywords match address, but does not match priority
        predicate = new PriorityContainsKeywordsPredicate("Main");
        assertFalse(predicate.test(new FlashcardBuilder().withPriority("low")
                .withDefinition("Main Street").build()));
    }
}
