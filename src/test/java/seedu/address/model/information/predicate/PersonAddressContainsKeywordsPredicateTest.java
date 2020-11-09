package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonAddressContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Yishun");
        List<String> secondPredicateKeywordList = Arrays.asList("Yishun", "Street");

        PersonAddressContainsKeywordsPredicate firstPredicate =
                new PersonAddressContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonAddressContainsKeywordsPredicate secondPredicate =
                new PersonAddressContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonAddressContainsKeywordsPredicate firstPredicateCopy =
                new PersonAddressContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        // One keyword
        PersonAddressContainsKeywordsPredicate predicate = new PersonAddressContainsKeywordsPredicate(
                Collections.singletonList("Yishun"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("Yishun Street 11").build()));

        // Multiple keywords
        predicate = new PersonAddressContainsKeywordsPredicate(Arrays.asList("Yishun", "Street"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("Yishun Street 11").build()));

        // Exact Matching
        predicate = new PersonAddressContainsKeywordsPredicate(Arrays.asList("Yishun", "Street", "11"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("Yishun Street 11").build()));

        // Zero keywords
        predicate = new PersonAddressContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new PersonBuilder().withAddress("Yishun Street 11").build()));

        // Mixed-case keywords
        predicate = new PersonAddressContainsKeywordsPredicate(Arrays.asList("YiShUn", "sTreEt", "11"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("Yishun Street 11").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Only one matching keyword, the rest does not match
        PersonAddressContainsKeywordsPredicate predicate = new PersonAddressContainsKeywordsPredicate(
                Arrays.asList("Tampines", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withAddress("Yishun Street 11").build()));

        // Non-matching keyword
        predicate = new PersonAddressContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withAddress("Yishun Street 11").build()));

        // Keywords match name, phone, and email, but does not match address
        predicate = new PersonAddressContainsKeywordsPredicate(
                Arrays.asList("Alice", "12345", "alice@email.com", "Tampines"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Yishun Street 11").build()));
    }
}
