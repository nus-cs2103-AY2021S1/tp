package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.JobBuilder;

public class JobPriorityContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("low");
        List<String> secondPredicateKeywordList = Arrays.asList("high");

        JobPriorityContainsKeywordsPredicate firstPredicate =
                new JobPriorityContainsKeywordsPredicate(firstPredicateKeywordList);
        JobPriorityContainsKeywordsPredicate secondPredicate =
                new JobPriorityContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        JobPriorityContainsKeywordsPredicate firstPredicateCopy =
                new JobPriorityContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different priority -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_priorityContainsKeyword_returnsTrue() {
        // Exactly matching keyword
        JobPriorityContainsKeywordsPredicate predicate =
                new JobPriorityContainsKeywordsPredicate(Collections.singletonList("high"));
        assertTrue(predicate.test(new JobBuilder().withPriority("high").build()));

        // Contains matching keyword
        predicate = new JobPriorityContainsKeywordsPredicate(Collections.singletonList("hi"));
        assertTrue(predicate.test(new JobBuilder().withPriority("high").build()));

        // Mixed-case keyword
        predicate = new JobPriorityContainsKeywordsPredicate(Collections.singletonList("HIgH"));
        assertTrue(predicate.test(new JobBuilder().withPriority("high").build()));

        // Zero keywords
        predicate = new JobPriorityContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new JobBuilder().withPriority("high").build()));
    }

    @Test
    public void test_priorityDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        JobPriorityContainsKeywordsPredicate predicate =
                new JobPriorityContainsKeywordsPredicate(Collections.singletonList("high"));
        assertFalse(predicate.test(new JobBuilder().withPriority("low").build()));
    }
}
