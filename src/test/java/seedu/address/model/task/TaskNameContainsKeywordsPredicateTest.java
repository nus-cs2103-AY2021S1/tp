package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.todolist.TaskBuilder;

public class TaskNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("second");
        List<String> secondPredicateKeywordList = Arrays.asList("second", "third");

        TaskNameContainsKeywordsPredicate firstPredicate =
                new TaskNameContainsKeywordsPredicate(firstPredicateKeywordList);
        TaskNameContainsKeywordsPredicate secondPredicate =
                new TaskNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskNameContainsKeywordsPredicate firstPredicateCopy =
                new TaskNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(4));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskNameContainsKeywords_returnsTrue() {
        // One keyword
        TaskNameContainsKeywordsPredicate predicate =
                new TaskNameContainsKeywordsPredicate(Collections.singletonList("Lab"));
        assertTrue(predicate.test(new TaskBuilder().withName("Lab 01").build()));

        // Multiple keywords
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("Lab", "01"));
        assertTrue(predicate.test(new TaskBuilder().withName("Lab 01").build()));

        // Only one matching keyword
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("Lab", "Assignment"));
        assertTrue(predicate.test(new TaskBuilder().withName("Lab 01").build()));

        // Mixed-case keywords
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("lAb", "assigNMeNt"));
        assertTrue(predicate.test(new TaskBuilder().withName("Lab Assignment").build()));
    }

    @Test
    public void test_taskNameDoesNotContainKeywords_returnsFalse() {

        // Non-matching keyword
        TaskNameContainsKeywordsPredicate predicate =
                new TaskNameContainsKeywordsPredicate(Arrays.asList("Lab"));
        assertFalse(predicate.test(new TaskBuilder().withName("Quiz 01").build()));

        // Keywords match date and priority, but does not match name
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("2020-01-01", "HIGH"));
        assertFalse(predicate.test(new TaskBuilder().withName("Lab 01")
                .withDate("2020-01-01").withPriority("HIGH").build()));
    }
}
