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

public class PhoneContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PhoneContainsKeywordsPredicate firstPredicate =
            new PhoneContainsKeywordsPredicate(firstPredicateKeywordList);
        PhoneContainsKeywordsPredicate secondPredicate =
            new PhoneContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        PhoneContainsKeywordsPredicate firstPredicateCopy =
            new PhoneContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different person -> returns false
        assertNotEquals(secondPredicate, firstPredicate);
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // One keyword
        PhoneContainsKeywordsPredicate predicate =
            new PhoneContainsKeywordsPredicate(Collections.singletonList("987654321"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("987654321").build()));

        // One sub-word
        predicate = new PhoneContainsKeywordsPredicate(Collections.singletonList("987"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("987654321").build()));

        // Multiple sub-words
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("987", "654"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("987654321").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Invalid keywords -> non numeric
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("abc", "def"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("987654321").build()));

        // Zero keywords
        predicate =
            new PhoneContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withPhone("987654321").build()));

        // Only one matching keyword -> fail
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("987", "123"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("987654321").build()));

        // Non-matching keyword
        predicate = new PhoneContainsKeywordsPredicate(Collections.singletonList("123456789"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("987654321").build()));

        // Keywords match name, email, department, and office, but does not match phone
        predicate = new PhoneContainsKeywordsPredicate(
            Arrays.asList("54321", "alice@email.com", "Alice", "Engineering", "E4-03-01"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice")
            .withPhone("12345").withEmail("alice@email.com")
            .withDepartment("Engineering").withOffice("E4-03-01").build()));
    }
}
