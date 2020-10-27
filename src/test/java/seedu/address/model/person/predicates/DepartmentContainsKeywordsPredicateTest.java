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

public class DepartmentContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DepartmentContainsKeywordsPredicate firstPredicate =
                new DepartmentContainsKeywordsPredicate(firstPredicateKeywordList);
        DepartmentContainsKeywordsPredicate secondPredicate =
                new DepartmentContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        DepartmentContainsKeywordsPredicate firstPredicateCopy =
                new DepartmentContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different person -> returns false
        assertNotEquals(secondPredicate, firstPredicate);
    }

    @Test
    public void test_departmentContainsKeywords_returnsTrue() {
        // One keyword
        DepartmentContainsKeywordsPredicate predicate =
                new DepartmentContainsKeywordsPredicate(Collections.singletonList("Computing"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("School of Computing").build()));

        // One sub-word
        predicate = new DepartmentContainsKeywordsPredicate(Collections.singletonList("Com"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Computing").build()));

        // Multiple keywords
        predicate = new DepartmentContainsKeywordsPredicate(Arrays.asList("Computer", "Science"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Computer Science").build()));

        // Multiple sub-words
        predicate = new DepartmentContainsKeywordsPredicate(Arrays.asList("Com", "Sci"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Computer Science").build()));

        // Mixed-case keywords
        predicate = new DepartmentContainsKeywordsPredicate(Arrays.asList("cOmPuTer", "SCiEnCe"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Computer Science").build()));
    }

    @Test
    public void test_departmentDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DepartmentContainsKeywordsPredicate predicate =
                new DepartmentContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withDepartment("Computing").build()));

        // Only one matching keyword
        predicate = new DepartmentContainsKeywordsPredicate(Arrays.asList("Science", "Engineering"));
        assertFalse(predicate.test(new PersonBuilder().withDepartment("Computer Engineering").build()));

        // Non-matching keyword
        predicate = new DepartmentContainsKeywordsPredicate(Collections.singletonList("Engineering"));
        assertFalse(predicate.test(new PersonBuilder().withDepartment("Computing Science").build()));

        // Keywords match name, phone, email, and office, but does not match department
        predicate = new DepartmentContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Alice", "Science", "E4-03-01"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice")
                .withPhone("12345").withEmail("alice@email.com")
                .withDepartment("Engineering").withOffice("E4-03-01").build()));
    }
}
