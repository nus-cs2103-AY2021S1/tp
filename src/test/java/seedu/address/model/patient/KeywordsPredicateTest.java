package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class KeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        KeywordsPredicate firstPredicate = new KeywordsPredicate(firstPredicateKeywordList);
        KeywordsPredicate secondPredicate = new KeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        KeywordsPredicate firstPredicateCopy = new KeywordsPredicate(firstPredicateKeywordList);
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
        KeywordsPredicate predicate = new KeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new KeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new KeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new KeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        KeywordsPredicate predicate = new KeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PatientBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new KeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new KeywordsPredicate(Arrays.asList("12345678", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PatientBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void test_nricMatchesKeywords_returnsTrue() {
        // One keyword
        KeywordsPredicate predicate = new KeywordsPredicate(Collections.singletonList("S1234567D"));
        assertTrue(predicate.test(new PatientBuilder().withNric("S1234567D").build()));

        // Multiple keywords
        predicate = new KeywordsPredicate(Arrays.asList("S1234567D", "S0000001A"));
        assertTrue(predicate.test(new PatientBuilder().withNric("S1234567D").build()));
        assertTrue(predicate.test(new PatientBuilder().withNric("S0000001A").build()));

        // Mixed-case keywords
        predicate = new KeywordsPredicate(Arrays.asList("s1234567d", "S0000001e"));
        assertTrue(predicate.test(new PatientBuilder().withNric("S1234567D").build()));
    }

    @Test
    public void test_nricDoesNotMatchKeywords_returnsFalse() {
        // Zero keywords
        KeywordsPredicate predicate = new KeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PatientBuilder().withNric("S1234567D").build()));

        // Non-matching keyword
        predicate = new KeywordsPredicate(Arrays.asList("S1234567D"));
        assertFalse(predicate.test(new PatientBuilder().withNric("S0000001A").build()));
        assertFalse(predicate.test(new PatientBuilder().withNric("S0000002B").build()));

        // Keywords match phone, email and address, but does not match nric
        predicate = new KeywordsPredicate(Arrays.asList("12345678", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PatientBuilder().withNric("S0000001A").withPhone("12345678")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void test_nameAndNricContainsKeywords_returnsTrue() {
        // Mix of name and nric keywords
        KeywordsPredicate predicate = new KeywordsPredicate(Arrays.asList("Alice", "S1234567D", "Bob", "S0000001A"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Li").build()));
        assertTrue(predicate.test(new PatientBuilder().withName("Bob Ng").build()));
        assertTrue(predicate.test(new PatientBuilder().withNric("S1234567D").build()));
        assertTrue(predicate.test(new PatientBuilder().withNric("S0000001A").build()));

        // Mixed-case keywords
        predicate = new KeywordsPredicate(Arrays.asList("alICe", "S1234567d", "bOB", "s0000001A"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Li").build()));
        assertTrue(predicate.test(new PatientBuilder().withName("Bob Ng").build()));
        assertTrue(predicate.test(new PatientBuilder().withNric("S1234567D").build()));
        assertTrue(predicate.test(new PatientBuilder().withNric("S0000001A").build()));
    }
}
