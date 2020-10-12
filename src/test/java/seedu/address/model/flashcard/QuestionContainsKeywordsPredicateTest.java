package seedu.address.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FlashcardBuilder;

public class QuestionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("oop");
        List<String> secondPredicateKeywordList = Arrays.asList("oop", "uml");

        QuestionContainsKeywordsPredicate firstPredicate = new
             QuestionContainsKeywordsPredicate(firstPredicateKeywordList);
        QuestionContainsKeywordsPredicate secondPredicate = new
             QuestionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        QuestionContainsKeywordsPredicate firstPredicateCopy = new QuestionContainsKeywordsPredicate(
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
    public void test_questionContainsKeywords_returnsTrue() {
        // One keyword
        QuestionContainsKeywordsPredicate predicate = new
            QuestionContainsKeywordsPredicate(Collections.singletonList("oop"));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("What is OOP ?").build()));

        // Multiple keywords
        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("association", "uml"));
        assertTrue(predicate.test(
            new FlashcardBuilder().withQuestion("How to show association in UML diagrams ?").build()));

        // Only one matching keyword
        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("OOP", "uml"));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("What is OOP ?").build()));

        // Mixed-case keywords
        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("oOp", "iS"));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("What is OOP ?").build()));
    }

    @Test
    public void test_questionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        QuestionContainsKeywordsPredicate predicate =
            new QuestionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("What is OOP ?").build()));

        // Non-matching keyword
        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("UML"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("What is OOP ?").build()));

        //Keywords match answer, but does not match question
        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("programming"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("What is OOP?")
                .withAnswer("programming paradigm").build()));
    }
}
