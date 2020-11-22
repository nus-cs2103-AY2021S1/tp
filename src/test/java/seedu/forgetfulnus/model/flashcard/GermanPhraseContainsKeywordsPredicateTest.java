package seedu.forgetfulnus.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.testutil.FlashCardBuilder;

public class GermanPhraseContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        GermanPhraseContainsKeywordsPredicate firstPredicate =
                new GermanPhraseContainsKeywordsPredicate(firstPredicateKeywordList);
        GermanPhraseContainsKeywordsPredicate secondPredicate =
                new GermanPhraseContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GermanPhraseContainsKeywordsPredicate firstPredicateCopy =
                new GermanPhraseContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different flashcard -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_germanPhraseContainsKeywords_returnsTrue() {
        // One keyword
        GermanPhraseContainsKeywordsPredicate predicate =
                new GermanPhraseContainsKeywordsPredicate(Collections.singletonList("Kaffee"));
        assertTrue(predicate.test(new FlashCardBuilder().withGermanPhrase("Kaffee Tee").build()));

        // Multiple keywords
        predicate = new GermanPhraseContainsKeywordsPredicate(Arrays.asList("Kaffee", "Tee"));
        assertTrue(predicate.test(new FlashCardBuilder().withGermanPhrase("Kaffee Tee").build()));

        // Only one matching keyword
        predicate = new GermanPhraseContainsKeywordsPredicate(Arrays.asList("Tee", "Wasser"));
        assertTrue(predicate.test(new FlashCardBuilder().withGermanPhrase("Kaffee Wasser").build()));

        // Mixed-case keywords
        predicate = new GermanPhraseContainsKeywordsPredicate(Arrays.asList("kAfFeE", "tEe"));
        assertTrue(predicate.test(new FlashCardBuilder().withGermanPhrase("Kaffee Tee").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GermanPhraseContainsKeywordsPredicate predicate =
                new GermanPhraseContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new FlashCardBuilder().withGermanPhrase("Kaffee").build()));

        // Non-matching keyword
        predicate = new GermanPhraseContainsKeywordsPredicate(Arrays.asList("Wasser"));
        assertFalse(predicate.test(new FlashCardBuilder().withGermanPhrase("Kaffee Tee").build()));

        // Keywords match english phrase, but does not match german phrase
        predicate = new GermanPhraseContainsKeywordsPredicate(Arrays.asList("Coffee"));
        assertFalse(predicate.test(new FlashCardBuilder().withGermanPhrase("Kaffee").withEnglishPhrase("Coffee")
                .build()));
    }
}
