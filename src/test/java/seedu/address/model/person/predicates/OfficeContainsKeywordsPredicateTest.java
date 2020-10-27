package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.builders.PersonBuilder;

public class OfficeContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        OfficeContainsKeywordsPredicate firstPredicate =
                new OfficeContainsKeywordsPredicate(firstPredicateKeywordList);
        OfficeContainsKeywordsPredicate secondPredicate =
                new OfficeContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        OfficeContainsKeywordsPredicate firstPredicateCopy =
                new OfficeContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different person -> returns false
        assertNotEquals(secondPredicate, firstPredicate);
    }

    @Test
    public void test_officeContainsKeywords_returnsTrue() {
        // One keyword
        OfficeContainsKeywordsPredicate predicate =
                new OfficeContainsKeywordsPredicate(Collections.singletonList("COM2"));
        assertTrue(predicate.test(new PersonBuilder().withOffice("COM2").build()));

        // One subword
        predicate = new OfficeContainsKeywordsPredicate(Collections.singletonList("COM2"));
        assertTrue(predicate.test(new PersonBuilder().withOffice("COM2-02-17").build()));

        // Multiple keywords
        predicate = new OfficeContainsKeywordsPredicate(Arrays.asList("COM2", "17"));
        assertTrue(predicate.test(new PersonBuilder().withOffice("COM2-02-17").build()));

        // Mixed-case keywords
        predicate = new OfficeContainsKeywordsPredicate(Arrays.asList("cOm", "SCi"));
        assertTrue(predicate.test(new PersonBuilder().withOffice("COM2 SCI17").build()));
    }

    @Test
    public void test_officeDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        OfficeContainsKeywordsPredicate predicate =
                new OfficeContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withOffice("COM2").build()));

        // Only one matching keyword
        predicate = new OfficeContainsKeywordsPredicate(Arrays.asList("S17", "COM2"));
        assertFalse(predicate.test(new PersonBuilder().withOffice("COM2-02-04").build()));

        // Non-matching keyword
        predicate = new OfficeContainsKeywordsPredicate(Collections.singletonList("E5"));
        assertFalse(predicate.test(new PersonBuilder().withOffice("COM2 S17").build()));

        // Keywords match name, phone, email, and department, but does not match office
        predicate = new OfficeContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Engineering", "Alice", "COM2-03-17"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice")
                .withPhone("12345").withEmail("alice@email.com")
                .withDepartment("Engineering").withOffice("E4-03-01").build()));
    }
}
