package seedu.address.model.person.hairdresser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.HairdresserBuilder;

public class HairdresserNameContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        HairdresserNameContainsKeywordsPredicate firstPredicate =
                new HairdresserNameContainsKeywordsPredicate(firstPredicateKeywordList);
        HairdresserNameContainsKeywordsPredicate secondPredicate =
                new HairdresserNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        HairdresserNameContainsKeywordsPredicate firstPredicateCopy =
                new HairdresserNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        HairdresserNameContainsKeywordsPredicate predicate =
                new HairdresserNameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new HairdresserBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new HairdresserNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new HairdresserBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new HairdresserNameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new HairdresserBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new HairdresserNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new HairdresserBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        HairdresserNameContainsKeywordsPredicate predicate =
                new HairdresserNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new HairdresserBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new HairdresserNameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new HairdresserBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new HairdresserNameContainsKeywordsPredicate(Arrays.asList("12345",
                "alice@email.com", "Senior", "Stylist"));
        assertFalse(predicate.test(new HairdresserBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withTitle("Senior Stylist").build()));
    }
}
