package seedu.flashcard.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.flashcard.testutil.FlashcardBuilder;

public class NoteContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("important");
        List<String> secondPredicateKeywordList = Arrays.asList("take", "note");

        NoteContainsKeywordsPredicate firstPredicate = new
                NoteContainsKeywordsPredicate(firstPredicateKeywordList);
        NoteContainsKeywordsPredicate secondPredicate = new
                NoteContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NoteContainsKeywordsPredicate firstPredicateCopy = new NoteContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_noteContainsKeywords_returnsTrue() {
        // One keyword
        NoteContainsKeywordsPredicate predicate = new
                NoteContainsKeywordsPredicate(Collections.singletonList("oop"));
        assertTrue(predicate.test(new FlashcardBuilder().withNote("oop").build()));

        // Multiple keywords
        predicate = new NoteContainsKeywordsPredicate(Arrays.asList("tested", "important"));
        assertTrue(predicate.test(new FlashcardBuilder().withNote("tested and important!").build()));

        // Only one matching keyword
        predicate = new NoteContainsKeywordsPredicate(Arrays.asList("tested", "important"));
        assertTrue(predicate.test(new FlashcardBuilder().withNote("tested").build()));

        // Mixed-case keywords
        predicate = new NoteContainsKeywordsPredicate(Arrays.asList("TesteD", "imPORTant"));
        assertTrue(predicate.test(new FlashcardBuilder().withNote("tested and important!").build()));

        // Note contains special characters
        predicate = new NoteContainsKeywordsPredicate(Collections.singletonList("tested"));
        assertTrue(predicate.test(new FlashcardBuilder().withNote("important-tested").build()));
    }

    @Test
    public void test_noteDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NoteContainsKeywordsPredicate predicate =
                new NoteContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new FlashcardBuilder().withNote("Tested").build()));

        // Empty Note
        predicate = new NoteContainsKeywordsPredicate(Collections.singletonList("Tested"));
        assertFalse(predicate.test(new FlashcardBuilder().withNote("").build()));

        // Non-matching keyword
        predicate = new NoteContainsKeywordsPredicate(Arrays.asList("Tested"));
        assertFalse(predicate.test(new FlashcardBuilder().withNote("Important").build()));

        //Keywords match category, but does not match note
        predicate = new NoteContainsKeywordsPredicate(Arrays.asList("Design"));
        assertFalse(predicate.test(new FlashcardBuilder().withCategory("Tested").build()));
    }
}
