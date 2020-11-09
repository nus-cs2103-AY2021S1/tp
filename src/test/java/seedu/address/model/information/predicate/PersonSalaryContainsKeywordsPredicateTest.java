package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonSalaryContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("3000.69");
        List<String> secondPredicateKeywordList = Arrays.asList("3000.69", "2154.77");

        PersonSalaryContainsKeywordsPredicate firstPredicate =
                new PersonSalaryContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonSalaryContainsKeywordsPredicate secondPredicate =
                new PersonSalaryContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonSalaryContainsKeywordsPredicate firstPredicateCopy =
                new PersonSalaryContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_salaryContainsKeywords_returnsTrue() {

        // Exact Matching
        PersonSalaryContainsKeywordsPredicate predicate = new PersonSalaryContainsKeywordsPredicate(
                Arrays.asList("3000"));
        assertTrue(predicate.test(new PersonBuilder().withSalary("3000").build()));

        // Zero keywords
        predicate =
                new PersonSalaryContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new PersonBuilder().withSalary("3000").build()));
    }

    @Test
    public void test_salaryDoesNotContainKeywords_returnsFalse() {

        // One keyword containing
        PersonSalaryContainsKeywordsPredicate predicate = new PersonSalaryContainsKeywordsPredicate(
                Collections.singletonList("3000"));
        assertFalse(predicate.test(new PersonBuilder().withSalary("3001").build()));

        // Only one matching keyword, the rest does not match
        predicate = new PersonSalaryContainsKeywordsPredicate(
                Arrays.asList("2154", "3000"));
        assertFalse(predicate.test(new PersonBuilder().withSalary("3000").build()));

        // Non-matching keyword
        predicate = new PersonSalaryContainsKeywordsPredicate(Arrays.asList("2154"));
        assertFalse(predicate.test(new PersonBuilder().withSalary("3000").build()));

        // Keywords match phone but does not match address
        predicate = new PersonSalaryContainsKeywordsPredicate(
                Arrays.asList("12345", "2122"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("12345").withSalary("3000").build()));
    }
}
