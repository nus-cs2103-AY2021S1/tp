package seedu.medmoriser.model.qanda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.medmoriser.testutil.QAndABuilder;

public class TagContainsKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy =
                new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different qAndA -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new QAndABuilder().withTags("Alice", "Bob").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new QAndABuilder().withTags("Alice", "Bob").build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new QAndABuilder().withTags("Alice", "Carol").build()));

        // Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new QAndABuilder().withTags("Alice", "Bob").build()));

        // Words with spacing
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Nervous System", "Cardiology"));
        assertTrue(predicate.test(new QAndABuilder().withTags("nervous System", "Hematology").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords (should not be possible for user to end up in this test case)
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new QAndABuilder().withTags("Alice").build()));

        // Zero keywords entered
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("t"));
        assertFalse(predicate.test(new QAndABuilder().withTags("Alice").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new QAndABuilder().withTags("Alice", "Bob").build()));

        // Keywords match question and answer, but does not match tag
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new QAndABuilder().withQuestion("Alice").withAnswer("Main Street")
                .withTags("Immunology").build()));
    }
}
