package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.contact.ContactBuilder;

public class ContactNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("second");
        List<String> secondPredicateKeywordList = Arrays.asList("second", "third");

        ContactNameContainsKeywordsPredicate firstPredicate =
                new ContactNameContainsKeywordsPredicate(firstPredicateKeywordList);
        ContactNameContainsKeywordsPredicate secondPredicate =
                new ContactNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContactNameContainsKeywordsPredicate firstPredicateCopy =
                new ContactNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(8));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_contactNameContainsKeywords_returnsTrue() {
        // One keyword
        ContactNameContainsKeywordsPredicate predicate =
                new ContactNameContainsKeywordsPredicate(Collections.singletonList("John"));
        assertTrue(predicate.test(new ContactBuilder().withName("John Doe").build()));

        // Multiple keywords
        predicate = new ContactNameContainsKeywordsPredicate(Arrays.asList("John", "Doe"));
        assertTrue(predicate.test(new ContactBuilder().withName("John Doe").build()));

        // Only one matching keyword
        predicate = new ContactNameContainsKeywordsPredicate(Arrays.asList("Amy", "Doe"));
        assertTrue(predicate.test(new ContactBuilder().withName("John Doe").build()));

        // Mixed-case keywords
        predicate = new ContactNameContainsKeywordsPredicate(Arrays.asList("joHn", "DOe"));
        assertTrue(predicate.test(new ContactBuilder().withName("John Doe").build()));
    }

    @Test
    public void test_contactNameDoesNotContainKeywords_returnsFalse() {

        // Non-matching keyword
        ContactNameContainsKeywordsPredicate predicate =
                new ContactNameContainsKeywordsPredicate(Arrays.asList("Amy"));
        assertFalse(predicate.test(new ContactBuilder().withName("John Doe").build()));

        // Keywords match email and telegram, but does not match name
        predicate = new ContactNameContainsKeywordsPredicate(Arrays.asList("john@email.com", "@johndoe"));
        assertFalse(predicate.test(new ContactBuilder().withName("Amy")
                .withEmail("john@email.com").withTelegram("@johndoe").build()));
    }
}
