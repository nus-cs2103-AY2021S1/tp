package seedu.flashcard.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.flashcard.testutil.FlashcardBuilder;

public class AnswerContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("oop");
        List<String> secondPredicateKeywordList = Arrays.asList("oop", "uml");

        AnswerContainsKeywordsPredicate firstPredicate = new
                AnswerContainsKeywordsPredicate(firstPredicateKeywordList);
        AnswerContainsKeywordsPredicate secondPredicate = new
                AnswerContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AnswerContainsKeywordsPredicate firstPredicateCopy = new AnswerContainsKeywordsPredicate(
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
    public void test_answerContainsKeywords_returnsTrue() {
        // One keyword
        AnswerContainsKeywordsPredicate predicate = new
                AnswerContainsKeywordsPredicate(Collections.singletonList("object"));
        assertTrue(predicate.test(new FlashcardBuilder().withAnswer("object oriented programming").build()));

        // Multiple keywords
        predicate = new AnswerContainsKeywordsPredicate(Arrays.asList("objected", "oriented"));
        assertTrue(predicate.test(
                new FlashcardBuilder().withAnswer("object oriented programming").build()));

        // Only one matching keyword
        predicate = new AnswerContainsKeywordsPredicate(Arrays.asList("object", "association"));
        assertTrue(predicate.test(
                new FlashcardBuilder().withAnswer("object oriented programming").build()));

        // Mixed-case keywords
        predicate = new AnswerContainsKeywordsPredicate(Arrays.asList("ObjeCT", "PrograMMING"));
        assertTrue(predicate.test(
                new FlashcardBuilder().withAnswer("object oriented programming").build()));
    }

    @Test
    public void test_answerDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AnswerContainsKeywordsPredicate predicate =
                new AnswerContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(
                new FlashcardBuilder().withAnswer("object oriented programming").build()));

        // Non-matching keyword
        predicate = new AnswerContainsKeywordsPredicate(Arrays.asList("UML"));
        assertFalse(predicate.test(
                new FlashcardBuilder().withAnswer("object oriented programming").build()));

        //Keywords match question, but does not match question
        predicate = new AnswerContainsKeywordsPredicate(Arrays.asList("oop"));
        assertFalse(predicate.test(
                new FlashcardBuilder().withQuestion("What is OOP?").build()));
    }
}
