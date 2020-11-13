package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TagBuilder;

public class TagNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagNameContainsKeywordsPredicate firstPredicate = new TagNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        TagNameContainsKeywordsPredicate secondPredicate = new TagNameContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagNameContainsKeywordsPredicate firstPredicateCopy = new TagNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tag -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        TagNameContainsKeywordsPredicate predicate = new TagNameContainsKeywordsPredicate(
                Collections.singletonList("Alice"));
        assertTrue(predicate.test(new TagBuilder().withTagName("Alice Bob").build()));

        // Multiple keywords
        predicate = new TagNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new TagBuilder().withTagName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new TagNameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new TagBuilder().withTagName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new TagNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new TagBuilder().withTagName("Alice Bob").build()));
    }

    @Test
    public void test_tagNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagNameContainsKeywordsPredicate predicate = new TagNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TagBuilder().withTagName("Alice").build()));

        // Non-matching keyword
        predicate = new TagNameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new TagBuilder().withTagName("Alice Bob").build()));

        // Keywords match phone and address, but does not match name
        predicate = new TagNameContainsKeywordsPredicate(Arrays.asList("12345", "Main", "Street"));
        assertFalse(predicate.test(new TagBuilder().withTagName("Alice")
                .withFileAddress("c:\\a\\b\\alice.txt").build()));
    }
}
