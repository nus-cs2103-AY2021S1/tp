package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class SchoolContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Changi");
        List<String> secondPredicateKeywordList = Arrays.asList("Kent", "Ridge");

        SchoolContainsKeywordsPredicate firstPredicate =
                new SchoolContainsKeywordsPredicate(firstPredicateKeywordList);
        SchoolContainsKeywordsPredicate secondPredicate =
                new SchoolContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SchoolContainsKeywordsPredicate firstPredicateCopy =
                new SchoolContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_schoolContainsAllKeywords_returnsTrue() {
        // One keyword different case
        SchoolContainsKeywordsPredicate predicate =
                new SchoolContainsKeywordsPredicate(Collections.singletonList("Changi"));
        assertTrue(predicate.test(new StudentBuilder().withSchool("changi").build()));

        // Multiple keywords same case
        predicate = new SchoolContainsKeywordsPredicate(Arrays.asList("Kent", "Ridge"));
        assertTrue(predicate.test(new StudentBuilder().withSchool("Kent Ridge").build()));

        // Mixed-case keywords
        predicate = new SchoolContainsKeywordsPredicate(Arrays.asList("KeNt", "RiDGE"));
        assertTrue(predicate.test(new StudentBuilder().withSchool("kent ridge").build()));

        // User guide test cases
        predicate = new SchoolContainsKeywordsPredicate(Arrays.asList("yishun", "sec"));
        assertTrue(predicate.test(new StudentBuilder().withSchool("Yishun Secondary School").build()));
        assertTrue(predicate.test(new StudentBuilder().withSchool("Yishun Town Secondary School").build()));

    }

    @Test
    public void test_schoolDoesNotContainAllKeywords_returnsFalse() {
        // Zero keywords
        SchoolContainsKeywordsPredicate predicate = new SchoolContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withSchool("Changi").build()));

        // Non-matching keyword
        predicate = new SchoolContainsKeywordsPredicate(Arrays.asList("Science"));
        assertFalse(predicate.test(new StudentBuilder().withSchool("School of Computing").build()));

        // One keyword matching one not matching
        predicate = new SchoolContainsKeywordsPredicate(Arrays.asList("Kent", "Ridge"));
        assertFalse(predicate.test(new StudentBuilder().withSchool("Kent Vale").build()));

        // Keywords match phone, email and address, but does not match school
        predicate = new SchoolContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withPhone("12345")
                .build()));

        // User guide test cases
        predicate = new SchoolContainsKeywordsPredicate(Arrays.asList("yishun", "secondary"));
        assertFalse(predicate.test(new StudentBuilder().withSchool("Yishun sec").build()));

    }
}
