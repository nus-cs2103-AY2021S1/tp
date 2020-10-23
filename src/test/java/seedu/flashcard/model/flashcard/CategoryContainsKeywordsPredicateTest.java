package seedu.flashcard.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.flashcard.testutil.FlashcardBuilder;

public class CategoryContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Design");
        List<String> secondPredicateKeywordList = Arrays.asList("Requirements", "Implementation");

        CategoryContainsKeywordsPredicate firstPredicate = new
                CategoryContainsKeywordsPredicate(firstPredicateKeywordList);
        CategoryContainsKeywordsPredicate secondPredicate = new
                CategoryContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CategoryContainsKeywordsPredicate firstPredicateCopy = new CategoryContainsKeywordsPredicate(
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
    public void test_categoryContainsKeywords_returnsTrue() {
        // One keyword
        CategoryContainsKeywordsPredicate predicate = new
                CategoryContainsKeywordsPredicate(Collections.singletonList("Design"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Design").build()));

        // Only one matching keyword
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("Design", "Implementation"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Design").build()));

        // Mixed-case keywords
        predicate = new CategoryContainsKeywordsPredicate(Collections.singletonList("DEsiGN"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Design").build()));

        // Partial matching of keywords
        predicate = new CategoryContainsKeywordsPredicate(Collections.singletonList("des"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Design").build()));
    }

    @Test
    public void test_categoryDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CategoryContainsKeywordsPredicate predicate =
                new CategoryContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new FlashcardBuilder().withCategory("Design").build()));

        // Non-matching keyword
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("Implementation"));
        assertFalse(predicate.test(new FlashcardBuilder().withCategory("Design").build()));

        //Keywords match question, but does not match category
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("oop"));
        assertFalse(predicate.test(
                new FlashcardBuilder().withQuestion("What is OOP?").build()));
    }
}
