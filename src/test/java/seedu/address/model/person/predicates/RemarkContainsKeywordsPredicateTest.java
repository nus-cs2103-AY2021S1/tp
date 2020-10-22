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

public class RemarkContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        RemarkContainsKeywordsPredicate firstPredicate =
                new RemarkContainsKeywordsPredicate(firstPredicateKeywordList);
        RemarkContainsKeywordsPredicate secondPredicate =
                new RemarkContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        RemarkContainsKeywordsPredicate firstPredicateCopy =
                new RemarkContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different person -> returns false
        assertNotEquals(secondPredicate, firstPredicate);
    }

    @Test
    public void test_remarkContainsKeywords_returnsTrue() {
        // One keyword
        RemarkContainsKeywordsPredicate predicate =
                new RemarkContainsKeywordsPredicate(Collections.singletonList("glasses"));
        assertTrue(predicate.test(new PersonBuilder().withRemark("wears glasses").build()));

        // Multiple keywords
        predicate = new RemarkContainsKeywordsPredicate(Arrays.asList("glasses", "bob-hair"));
        assertTrue(predicate.test(new PersonBuilder().withRemark("glasses bob-hair").build()));

        // Mixed-case keywords
        predicate = new RemarkContainsKeywordsPredicate(Arrays.asList("gLaSsEs", "bOB-HAiR"));
        assertTrue(predicate.test(new PersonBuilder().withRemark("glasses bob-hair").build()));
    }

    @Test
    public void test_remarkDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RemarkContainsKeywordsPredicate predicate =
                new RemarkContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withRemark("glasses").build()));

        // Only one matching keyword
        predicate = new RemarkContainsKeywordsPredicate(Arrays.asList("bob-hair", "beard"));
        assertFalse(predicate.test(new PersonBuilder().withRemark("glasses beard").build()));

        // Non-matching keyword
        predicate = new RemarkContainsKeywordsPredicate(Collections.singletonList("beard"));
        assertFalse(predicate.test(new PersonBuilder().withRemark("glasses bob-hair").build()));

        // Keywords match name, phone, email, department and office, but does not match remark
        predicate = new RemarkContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Alice", "Computing", "COM2-03-01"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withDepartment("Computing").withOffice("COM2-03-01")
                .withRemark("glasses").build()));
    }
}
