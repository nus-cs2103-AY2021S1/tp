package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonApplicationContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("19-10-12");
        List<String> secondPredicateKeywordList = Arrays.asList("19-10-12", "20-01-20");

        PersonApplicationContainsKeywordsPredicate firstPredicate =
                new PersonApplicationContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonApplicationContainsKeywordsPredicate secondPredicate =
                new PersonApplicationContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonApplicationContainsKeywordsPredicate firstPredicateCopy =
                new PersonApplicationContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_applicationContainsKeywords_returnsTrue() {
        // One keyword
        PersonApplicationContainsKeywordsPredicate predicate = new PersonApplicationContainsKeywordsPredicate(
                Collections.singletonList("19"));
        assertTrue(predicate.test(new PersonBuilder().withDateOfApplication("19-10-12").build()));

        // Multiple keywords
        predicate = new PersonApplicationContainsKeywordsPredicate(Arrays.asList("19", "12"));
        assertTrue(predicate.test(new PersonBuilder().withDateOfApplication("19-10-12").build()));

        // Exact Matching
        predicate = new PersonApplicationContainsKeywordsPredicate(Arrays.asList("19-10-12"));
        assertTrue(predicate.test(new PersonBuilder().withDateOfApplication("19-10-12").build()));

        // Zero keywords
        predicate = new PersonApplicationContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new PersonBuilder().withDateOfApplication("19-10-12").build()));

    }

    @Test
    public void test_applicationDoesNotContainKeywords_returnsFalse() {
        // Only one matching keyword, the rest does not match
        PersonApplicationContainsKeywordsPredicate predicate = new PersonApplicationContainsKeywordsPredicate(
                Arrays.asList("19", "20", "31"));
        assertFalse(predicate.test(new PersonBuilder().withDateOfApplication("19-10-12").build()));

        // Non-matching keyword
        predicate = new PersonApplicationContainsKeywordsPredicate(Arrays.asList("20-01-20"));
        assertFalse(predicate.test(new PersonBuilder().withDateOfApplication("19-10-12").build()));

        // Keywords match name, phone, and email, but does not match date of application
        predicate = new PersonApplicationContainsKeywordsPredicate(
                Arrays.asList("Alice", "12345", "alice@email.com", "20-01-20"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withDateOfApplication("19-10-12").build()));
    }
}
