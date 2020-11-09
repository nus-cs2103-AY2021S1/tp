package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FlashcardBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate("first");
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate("second");

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy = new TagContainsKeywordsPredicate("first");
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different flashcard -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate("Hashing");
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Sorting", "Hashing").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate("");
        assertFalse(predicate.test(new FlashcardBuilder().withTags("Hashing").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate("Heaps");
        assertFalse(predicate.test(new FlashcardBuilder().withTags("Hashing", "Sorting").build()));

        // Keywords match address, but does not match tag
        predicate = new TagContainsKeywordsPredicate("Main");
        assertFalse(predicate.test(new FlashcardBuilder().withTags("Hashing")
                .withDefinition("Main Street").build()));
    }
}
