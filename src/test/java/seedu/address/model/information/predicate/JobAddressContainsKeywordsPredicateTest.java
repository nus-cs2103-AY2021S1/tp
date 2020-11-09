package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.JobBuilder;

public class JobAddressContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Yishun");
        List<String> secondPredicateKeywordList = Arrays.asList("Yishun", "Street");

        JobAddressContainsKeywordsPredicate firstPredicate =
                new JobAddressContainsKeywordsPredicate(firstPredicateKeywordList);
        JobAddressContainsKeywordsPredicate secondPredicate =
                new JobAddressContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        JobAddressContainsKeywordsPredicate firstPredicateCopy =
                new JobAddressContainsKeywordsPredicate(firstPredicateKeywordList);
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
        JobAddressContainsKeywordsPredicate predicate = new JobAddressContainsKeywordsPredicate(
                Collections.singletonList("Yishun"));
        assertTrue(predicate.test(new JobBuilder().withAddress("Yishun Street 11").build()));

        // Multiple keywords
        predicate = new JobAddressContainsKeywordsPredicate(Arrays.asList("Yishun", "Street"));
        assertTrue(predicate.test(new JobBuilder().withAddress("Yishun Street 11").build()));

        // Exact Matching
        predicate = new JobAddressContainsKeywordsPredicate(Arrays.asList("Yishun", "Street", "11"));
        assertTrue(predicate.test(new JobBuilder().withAddress("Yishun Street 11").build()));

        // Zero keywords
        predicate = new JobAddressContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new JobBuilder().withAddress("Yishun Street 11").build()));

        // Mixed-case keywords
        predicate = new JobAddressContainsKeywordsPredicate(Arrays.asList("yIsHuN", "sTrEet", "11"));
        assertTrue(predicate.test(new JobBuilder().withAddress("Yishun Street 11").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {

        // Only one matching keyword, the other does not match
        JobAddressContainsKeywordsPredicate predicate = new JobAddressContainsKeywordsPredicate(
                Arrays.asList("Tampines", "Street"));
        assertFalse(predicate.test(new JobBuilder().withAddress("Yishun Street 11").build()));

        // Non-matching keyword
        predicate = new JobAddressContainsKeywordsPredicate(Arrays.asList("Tampines"));
        assertFalse(predicate.test(new JobBuilder().withAddress("Yishun Street 11").build()));

        // Keywords match job title, phone and email, but does not match address
        predicate = new JobAddressContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Cleaner", "Tampines"));
        assertFalse(predicate.test(new JobBuilder().withJobTitle("Cleaner").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Yishun Street 11").build()));
    }
}
