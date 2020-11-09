package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.JobBuilder;

public class JobJobTitleContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("toilet");
        List<String> secondPredicateKeywordList = Arrays.asList("toilet", "bowl");

        JobJobTitleContainsKeywordsPredicate firstPredicate =
                new JobJobTitleContainsKeywordsPredicate(firstPredicateKeywordList);
        JobJobTitleContainsKeywordsPredicate secondPredicate =
                new JobJobTitleContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        JobJobTitleContainsKeywordsPredicate firstPredicateCopy =
                new JobJobTitleContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_jobTitleContainsKeywords_returnsTrue() {
        // One keyword
        JobJobTitleContainsKeywordsPredicate predicate = new JobJobTitleContainsKeywordsPredicate(
                Collections.singletonList("Toilet"));
        assertTrue(predicate.test(new JobBuilder().withJobTitle("Toilet Bowl Cleaner").build()));

        // Multiple keywords
        predicate = new JobJobTitleContainsKeywordsPredicate(Arrays.asList("Toilet", "Bowl"));
        assertTrue(predicate.test(new JobBuilder().withJobTitle("Toilet Bowl Cleaner").build()));

        // Exact Matching
        predicate = new JobJobTitleContainsKeywordsPredicate(Arrays.asList("Toilet", "Bowl", "Cleaner"));
        assertTrue(predicate.test(new JobBuilder().withJobTitle("Toilet Bowl Cleaner").build()));

        // Zero keywords
        predicate = new JobJobTitleContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new JobBuilder().withJobTitle("Toilet Bowl Cleaner").build()));

        // Mixed-case keywords
        predicate = new JobJobTitleContainsKeywordsPredicate(Arrays.asList("tOiLeT", "bOwL", "cLeAnEr"));
        assertTrue(predicate.test(new JobBuilder().withJobTitle("Toilet Bowl Cleaner").build()));
    }

    @Test
    public void test_jobTitleDoesNotContainKeywords_returnsFalse() {

        // Only one matching keyword, the other does not match
        JobJobTitleContainsKeywordsPredicate predicate = new JobJobTitleContainsKeywordsPredicate(
                Arrays.asList("Toilet", "Wiper"));
        assertFalse(predicate.test(new JobBuilder().withJobTitle("Toilet Bowl Cleaner").build()));

        // Non-matching keyword
        predicate = new JobJobTitleContainsKeywordsPredicate(Arrays.asList("Engineer"));
        assertFalse(predicate.test(new JobBuilder().withJobTitle("Toilet Bowl Cleaner").build()));

        // Keywords match phone, email and address, but does not match job title
        predicate = new JobJobTitleContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street", "CEO"));
        assertFalse(predicate.test(new JobBuilder().withJobTitle("Cleaner").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
