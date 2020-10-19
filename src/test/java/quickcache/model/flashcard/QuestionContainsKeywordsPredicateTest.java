package quickcache.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import quickcache.testutil.FlashcardBuilder;

class QuestionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = prepareKeywordList("first");
        List<String> secondPredicateKeywordList = prepareKeywordList("first", "second");

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

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_questionContainsKeywords_returnsTrue() {
        // One keyword
        QuestionContainsKeywordsPredicate predicate =
            new QuestionContainsKeywordsPredicate(prepareKeywordList("CS1101S?"));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("What is CS1101S?").build()));

        // Multiple keywords
        predicate = new QuestionContainsKeywordsPredicate(prepareKeywordList("What", "is", "CS1101S?"));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("What is CS1101S?").build()));

        // Mixed-case keywords
        predicate = new QuestionContainsKeywordsPredicate(prepareKeywordList("WhAt", "iS", "Cs1101s?"));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("What is CS1101S?").build()));
    }

    @Test
    public void test_questionDoesNotContainKeywords_returnsFalse() {
        // Only one matching keyword
        QuestionContainsKeywordsPredicate predicate =
                new QuestionContainsKeywordsPredicate(prepareKeywordList("What", "CS2103T"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("What is CS1101S?").build()));

        // Non-matching keyword
        predicate = new QuestionContainsKeywordsPredicate(prepareKeywordList("Carol"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("What is CS1101S?").build()));
    }

    private List<String> prepareKeywordList(String... keywords) {
        return Arrays.asList(keywords);
    }

}
