package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.JobBuilder;

public class JobEmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("@recruitment.com");
        List<String> secondPredicateKeywordList = Arrays.asList("facebook@recruitment.com");

        JobEmailContainsKeywordsPredicate firstPredicate =
                new JobEmailContainsKeywordsPredicate(firstPredicateKeywordList);
        JobEmailContainsKeywordsPredicate secondPredicate =
                new JobEmailContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        JobEmailContainsKeywordsPredicate firstPredicateCopy =
                new JobEmailContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different email -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeyword_returnsTrue() {
        // Exactly matching keyword
        JobEmailContainsKeywordsPredicate predicate =
                new JobEmailContainsKeywordsPredicate(Collections.singletonList("google@recruitment.com"));
        assertTrue(predicate.test(new JobBuilder().withEmail("google@recruitment.com").build()));

        // Contains matching keyword
        predicate = new JobEmailContainsKeywordsPredicate(Collections.singletonList("@recruitment.com"));
        assertTrue(predicate.test(new JobBuilder().withEmail("google@recruitment.com").build()));

        // Mixed-case keyword
        predicate = new JobEmailContainsKeywordsPredicate(Collections.singletonList("FaCeBOOk@reCrUItMenT.Com"));
        assertTrue(predicate.test(new JobBuilder().withEmail("facebook@recruitment.com").build()));

        // Zero keywords
        predicate = new JobEmailContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new JobBuilder().withEmail("facebook@recruitment.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        JobEmailContainsKeywordsPredicate predicate =
                new JobEmailContainsKeywordsPredicate(Collections.singletonList("@recruitment.sg"));
        assertFalse(predicate.test(new JobBuilder().withEmail("facebook@recruitment.com").build()));
    }
}
