package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.JobBuilder;

public class JobPhoneContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("9191");
        List<String> secondPredicateKeywordList = Arrays.asList("91912020");

        JobPhoneContainsKeywordsPredicate firstPredicate =
                new JobPhoneContainsKeywordsPredicate(firstPredicateKeywordList);
        JobPhoneContainsKeywordsPredicate secondPredicate =
                new JobPhoneContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        JobPhoneContainsKeywordsPredicate firstPredicateCopy =
                new JobPhoneContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different phone number -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsKeyword_returnsTrue() {
        // Exactly matching keyword
        JobPhoneContainsKeywordsPredicate predicate =
                new JobPhoneContainsKeywordsPredicate(Collections.singletonList("91910202"));
        assertTrue(predicate.test(new JobBuilder().withPhone("91910202").build()));

        // Zero keywords
        predicate = new JobPhoneContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new JobBuilder().withPhone("91910202").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        JobPhoneContainsKeywordsPredicate predicate =
                new JobPhoneContainsKeywordsPredicate(Collections.singletonList("9191"));
        assertFalse(predicate.test(new JobBuilder().withPhone("91910000").build()));
        assertFalse(predicate.test(new JobBuilder().withPhone("91000091").build()));
    }
}
