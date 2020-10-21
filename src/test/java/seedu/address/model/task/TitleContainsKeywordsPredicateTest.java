package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ToDoBuilder;

public class TitleContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TitleContainsKeywordsPredicate firstPredicate = new TitleContainsKeywordsPredicate(firstPredicateKeywordList);
        TitleContainsKeywordsPredicate secondPredicate = new TitleContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TitleContainsKeywordsPredicate firstPredicateCopy = new TitleContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_titleContainsKeywords_returnsTrue() {
        // One keyword
        TitleContainsKeywordsPredicate predicate = new TitleContainsKeywordsPredicate(
                Collections.singletonList("Lecture"));
        assertTrue(predicate.test(new ToDoBuilder().withTitle("Weekly Lecture").build()));

        // Multiple keywords
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("CS2103", "Project"));
        assertTrue(predicate.test(new ToDoBuilder().withTitle("CS2103 Project").build()));

        // Only one matching keyword
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("CS2103", "Project"));
        assertTrue(predicate.test(new ToDoBuilder().withTitle("CS2103 Lecture").build()));

        // Mixed-case keywords
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("cS2103", "PrOjEcT"));
        assertTrue(predicate.test(new ToDoBuilder().withTitle("CS2103 Project").build()));
    }

    @Test
    public void test_titleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TitleContainsKeywordsPredicate predicate = new TitleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ToDoBuilder().withTitle("Lecture").build()));

        // Non-matching keyword
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Lecture"));
        assertFalse(predicate.test(new ToDoBuilder().withTitle("CS2103 Project").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("12345", "random description", "Low"));
        assertFalse(predicate.test(new ToDoBuilder().withTitle("Lecture").withDescription("random description")
                .withPriority("Low").build()));
    }
}
