package quickcache.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import quickcache.testutil.FlashcardBuilder;

class FlashcardContainsTagPredicateTest {

    @Test
    public void equals() {
        Set<Tag> firstPredicateTagSet = prepareTagSet("first");
        Set<Tag> secondPredicateTagSet = prepareTagSet("first", "second");

        FlashcardContainsTagPredicate firstPredicate = new FlashcardContainsTagPredicate(firstPredicateTagSet);
        FlashcardContainsTagPredicate secondPredicate = new FlashcardContainsTagPredicate(secondPredicateTagSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FlashcardContainsTagPredicate firstPredicateCopy = new FlashcardContainsTagPredicate(firstPredicateTagSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_flashcardContainsTags_returnsTrue() {
        // One keyword
        FlashcardContainsTagPredicate predicate =
            new FlashcardContainsTagPredicate(prepareTagSet("Programming"));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Programming", "English").build()));

        // Multiple keywords
        predicate = new FlashcardContainsTagPredicate(prepareTagSet("Programming", "English"));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Programming", "English").build()));
    }

    @Test
    public void test_flashcardDoesNotContainTags_returnsFalse() {
        // Non-matching keyword
        FlashcardContainsTagPredicate predicate = new FlashcardContainsTagPredicate(prepareTagSet("Carol"));
        assertFalse(predicate.test(new FlashcardBuilder().withTags("Programming", "English").build()));

        // Only one matching keyword
        predicate = new FlashcardContainsTagPredicate(prepareTagSet("Programming", "Carol"));
        assertFalse(predicate.test(new FlashcardBuilder().withTags("Programming", "English").build()));

        // Mixed-case keywords
        predicate = new FlashcardContainsTagPredicate(prepareTagSet("pRogramming", "EnGlish"));
        assertFalse(predicate.test(new FlashcardBuilder().withTags("Programming", "English").build()));
    }

    private Set<Tag> prepareTagSet(String... tags) {
        HashSet<Tag> tagSet = new HashSet<>();
        for (String tag: tags) {
            tagSet.add(new Tag(tag));
        }
        return tagSet;
    }
}
