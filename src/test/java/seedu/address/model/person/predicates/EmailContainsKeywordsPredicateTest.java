package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EmailContainsKeywordsPredicate firstPredicate =
            new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        EmailContainsKeywordsPredicate secondPredicate =
            new EmailContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy =
            new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different person -> returns false
        assertNotEquals(secondPredicate, firstPredicate);
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        EmailContainsKeywordsPredicate predicate =
            new EmailContainsKeywordsPredicate(Collections.singletonList("alice@email.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("alice@email.com").build()));

        // Multiple sub-words
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("alice", "email.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("alice@email.com").build()));

        // Mixed-case keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("aLIce", "eMaIL.CoM"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("alice@email.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withEmail("alice@email.com").build()));

        // Only one matching keyword -> fail
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("alice", "gmail.com"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("alice@email.com").build()));

        // Non-matching keyword
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("bob"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("alice@email.com").build()));

        // Keywords match name, phone, department and office, but does not match email
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("Alice", "bob@email.com", "12345", "Computer",
            "Science", "COM2-03-01"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
            .withEmail("alice@email.com").withDepartment("Computer Science").withOffice("COM2-03-01").build()));
    }
}
