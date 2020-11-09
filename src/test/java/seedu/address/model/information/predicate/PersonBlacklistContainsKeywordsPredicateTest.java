package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonBlacklistContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("true");
        List<String> secondPredicateKeywordList = Arrays.asList("true", "false");

        PersonBlacklistContainsKeywordsPredicate firstPredicate =
                new PersonBlacklistContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonBlacklistContainsKeywordsPredicate secondPredicate =
                new PersonBlacklistContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonBlacklistContainsKeywordsPredicate firstPredicateCopy =
                new PersonBlacklistContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_blacklistContainsKeywords_returnsTrue() {
        // keyword containing
        PersonBlacklistContainsKeywordsPredicate predicate = new PersonBlacklistContainsKeywordsPredicate(
                Collections.singletonList("tru"));
        assertTrue(predicate.test(new PersonBuilder().withBlacklistStatus("true").build()));

        // Exact Matching
        predicate = new PersonBlacklistContainsKeywordsPredicate(Arrays.asList("true"));
        assertTrue(predicate.test(new PersonBuilder().withBlacklistStatus("true").build()));

        // Zero keywords
        predicate = new PersonBlacklistContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new PersonBuilder().withBlacklistStatus("true").build()));

        // Mixed-case keywords
        predicate = new PersonBlacklistContainsKeywordsPredicate(Arrays.asList("tRuE"));
        assertTrue(predicate.test(new PersonBuilder().withBlacklistStatus("true").build()));
    }

    @Test
    public void test_blacklistDoesNotContainKeywords_returnsFalse() {
        // Extra input
        PersonBlacklistContainsKeywordsPredicate predicate = new PersonBlacklistContainsKeywordsPredicate(
                Arrays.asList("true", "false"));
        assertFalse(predicate.test(new PersonBuilder().withBlacklistStatus("true").build()));

        // Non-matching keyword
        predicate = new PersonBlacklistContainsKeywordsPredicate(Arrays.asList("false"));
        assertFalse(predicate.test(new PersonBuilder().withBlacklistStatus("true").build()));

        // Keywords match name, phone, and email, but does not match blacklist status.
        predicate = new PersonBlacklistContainsKeywordsPredicate(
                Arrays.asList("Alice", "12345", "alice@email.com", "false"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withBlacklistStatus("true").build()));
    }
}
