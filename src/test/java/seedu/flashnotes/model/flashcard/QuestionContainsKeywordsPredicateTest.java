package seedu.flashnotes.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.testutil.FlashcardBuilder;

public class QuestionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        QuestionContainsKeywordsPredicate firstPredicate =
                new QuestionContainsKeywordsPredicate(firstPredicateKeywordList);
        QuestionContainsKeywordsPredicate secondPredicate =
                new QuestionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        QuestionContainsKeywordsPredicate firstPredicateCopy =
                new QuestionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different flashcard -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_questionContainsKeywords_returnsTrue() {
        // One keyword
        QuestionContainsKeywordsPredicate predicate =
                new QuestionContainsKeywordsPredicate(Collections.singletonList("Why"));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Why How").build()));

        // Multiple keywords
        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("Why", "How"));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Why How").build()));

        // Only one matching keyword
        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("How", "Who"));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Why Who").build()));

        // Mixed-case keywords
        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("wHy", "hOw"));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Why How").build()));
    }

    @Test
    public void test_questionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        QuestionContainsKeywordsPredicate predicate = new QuestionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Why").build()));

        // Non-matching keyword
        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("Who"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Why How").build()));

        // Keywords match answer and tags, but does not match question
        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Why").withAnswer("12345").withTag("Main")
                .build()));
    }
}
