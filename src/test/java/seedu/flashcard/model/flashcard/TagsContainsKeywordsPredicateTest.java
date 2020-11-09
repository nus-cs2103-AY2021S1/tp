package seedu.flashcard.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.flashcard.testutil.FlashcardBuilder;

public class TagsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("oop");
        List<String> secondPredicateKeywordList = Arrays.asList("oop", "uml");

        TagsContainsKeywordsPredicate firstPredicate = new
                TagsContainsKeywordsPredicate(firstPredicateKeywordList);
        TagsContainsKeywordsPredicate secondPredicate = new
                TagsContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainsKeywordsPredicate firstPredicateCopy = new TagsContainsKeywordsPredicate(
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
    public void test_tagsContainsKeywords_returnsTrue() {
        // One keyword
        TagsContainsKeywordsPredicate predicate = new
                TagsContainsKeywordsPredicate(Collections.singletonList("design"));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("design").build()));

        // Multiple keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("association", "uml"));
        assertTrue(predicate.test(
                new FlashcardBuilder().withTags("association", "uml").build()));

        // Only one matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("OOP", "uml"));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("association", "uml").build()));

        // Mixed-case keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("uML", "AssoCIATION"));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("association", "uml").build()));

    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagsContainsKeywordsPredicate predicate =
                new TagsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new FlashcardBuilder().withTags("association", "uml").build()));

        // Non-matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("design"));
        assertFalse(predicate.test(new FlashcardBuilder().withTags("association", "uml").build()));

        //Keywords match question, but does not match tags
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("OOP"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("What is OOP?")
                .withTags("tested").build()));
    }

}
