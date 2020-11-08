package quickcache.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import quickcache.testutil.FlashcardBuilder;

public class FlashcardPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = prepareKeywordList("first");
        List<String> secondPredicateKeywordList = prepareKeywordList("first", "second");

        Set<Tag> firstPredicateTagSet = prepareTagSet("first");
        Set<Tag> secondPredicateTagSet = prepareTagSet("first", "second");

        FlashcardPredicate firstPredicate = preparePredicate(firstPredicateTagSet, firstPredicateKeywordList);
        FlashcardPredicate secondPredicate = preparePredicate(secondPredicateTagSet, secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FlashcardPredicate firstPredicateCopy = preparePredicate(firstPredicateTagSet, firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_encapsulatesQuestionContainsKeywordsPredicate() {
        // One keyword
        FlashcardPredicate predicate = preparePredicate(prepareTagSet(), prepareKeywordList("CS1101S?"));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("What is CS1101S?").build()));

        // Multiple keywords
        predicate = preparePredicate(prepareTagSet(), prepareKeywordList("What", "is", "CS1101S?"));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("What is CS1101S?").build()));

        // Mixed-case keywords
        predicate = preparePredicate(prepareTagSet(), prepareKeywordList("WhAt", "iS", "Cs1101s?"));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("What is CS1101S?").build()));

        // Only one matching keyword
        predicate = preparePredicate(prepareTagSet(), prepareKeywordList("What", "CS2103T"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("What is CS1101S?").build()));

        // Non-matching keyword
        predicate = preparePredicate(prepareTagSet(), prepareKeywordList("Carol"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("What is CS1101S?").build()));
    }

    @Test
    public void test_encapsulatesFlashcardContainsTagsPredicate() {
        // One keyword
        FlashcardPredicate predicate = preparePredicate(prepareTagSet("Programming"), prepareKeywordList());
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Programming", "English").build()));

        // Multiple keywords
        predicate = preparePredicate(prepareTagSet("Programming", "English"), prepareKeywordList());
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Programming", "English").build()));

        // Non-matching keyword
        predicate = preparePredicate(prepareTagSet("Carol"), prepareKeywordList());
        assertFalse(predicate.test(new FlashcardBuilder().withTags("Programming", "English").build()));

        // Only one matching keyword
        predicate = preparePredicate(prepareTagSet("Programming", "Carol"), prepareKeywordList());
        assertFalse(predicate.test(new FlashcardBuilder().withTags("Programming", "English").build()));

        // Mixed-case keywords
        predicate = preparePredicate(prepareTagSet("pRogramming", "EnGlish"), prepareKeywordList());
        assertFalse(predicate.test(new FlashcardBuilder().withTags("Programming", "English").build()));
    }

    @Test
    public void test_encapsulatesTwoPredicates() {
        // Matching question keyword and tag keyword
        FlashcardPredicate predicate = preparePredicate(prepareTagSet("Programming"), prepareKeywordList("Who"));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Who is this?")
                .withTags("Programming", "English").build()));

        // Non-matching question keyword and matching tag keyword
        predicate = preparePredicate(prepareTagSet("Programming"), prepareKeywordList("Why"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Who is this?")
                .withTags("Programming", "English").build()));

        // Matching question keyword and non-matching tag keyword
        predicate = preparePredicate(prepareTagSet("Carol"), prepareKeywordList("Who"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Who is this?")
                .withTags("Programming", "English").build()));

        // Non-matching question keyword and non-matching tag keyword
        predicate = preparePredicate(prepareTagSet("Carol"), prepareKeywordList("Why"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Who is this?")
                .withTags("Programming", "English").build()));
    }

    @Test
    public void constructor_invalidArguments_throwException() {
        ArrayList<Predicate<Flashcard>> invalidPredicateList = new ArrayList<>();
        invalidPredicateList.add(new FlashcardContainsTagPredicate(prepareTagSet()));
        invalidPredicateList.add(null);
        invalidPredicateList.add(new QuestionContainsKeywordsPredicate(prepareKeywordList()));

        // Invalid arguments for constructor
        assertThrows(NullPointerException.class, () -> new FlashcardPredicate(null));
        assertThrows(IllegalArgumentException.class, () -> new FlashcardPredicate(invalidPredicateList));
    }

    private Set<Tag> prepareTagSet(String... tags) {
        HashSet<Tag> tagSet = new HashSet<>();
        for (String tag: tags) {
            tagSet.add(new Tag(tag));
        }
        return tagSet;
    }

    private List<String> prepareKeywordList(String... keywords) {
        return Arrays.asList(keywords);
    }

    /**
     * Parses {@code Set} of {@code Tag} and {@code List} of keywords into a {@code FlashcardPredicate}.
     */
    private FlashcardPredicate preparePredicate(Set<Tag> tagsToMatch, List<String> questionKeywords) {
        ArrayList<Predicate<Flashcard>> predicates = new ArrayList<>();

        if (!tagsToMatch.isEmpty()) {
            predicates.add(new FlashcardContainsTagPredicate(tagsToMatch));
        }

        if (!questionKeywords.isEmpty()) {
            predicates.add(new QuestionContainsKeywordsPredicate(questionKeywords));
        }
        return new FlashcardPredicate(predicates);
    }
}
