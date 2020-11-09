package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.JobBuilder;

public class JobCompanyNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Mr");
        List<String> secondPredicateKeywordList = Arrays.asList("Mr", "Bean");

        JobCompanyNameContainsKeywordsPredicate firstPredicate =
                new JobCompanyNameContainsKeywordsPredicate(firstPredicateKeywordList);
        JobCompanyNameContainsKeywordsPredicate secondPredicate =
                new JobCompanyNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        JobCompanyNameContainsKeywordsPredicate firstPredicateCopy =
                new JobCompanyNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different companies -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_companyNameContainsKeywords_returnsTrue() {
        // One keyword
        JobCompanyNameContainsKeywordsPredicate predicate =
                new JobCompanyNameContainsKeywordsPredicate(Arrays.asList("Facebook"));
        assertTrue(predicate.test(new JobBuilder().withCompanyName("Facebook").build()));

        // Multiple keywords
        predicate = new JobCompanyNameContainsKeywordsPredicate(Arrays.asList("Mr", "Bean"));
        assertTrue(predicate.test(new JobBuilder().withCompanyName("Mr Bean").build()));

        // Contain keyword
        predicate = new JobCompanyNameContainsKeywordsPredicate(Arrays.asList("Bean"));
        assertTrue(predicate.test(new JobBuilder().withCompanyName("Mr Bean").build()));
        assertTrue(predicate.test(new JobBuilder().withCompanyName("Coffee Bean Tea Leaf").build()));

        // Mixed-case keywords
        predicate = new JobCompanyNameContainsKeywordsPredicate(Arrays.asList("gOOgLe"));
        assertTrue(predicate.test(new JobBuilder().withCompanyName("Google").build()));

        // Zero keywords
        predicate = new JobCompanyNameContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new JobBuilder().withCompanyName("Google").build()));
    }

    @Test
    public void test_companyNameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        JobCompanyNameContainsKeywordsPredicate predicate =
                new JobCompanyNameContainsKeywordsPredicate(Arrays.asList("Facebook"));
        assertFalse(predicate.test(new JobBuilder().withCompanyName("Google").build()));

        // Some matching keywords only
        // Multiple keywords
        predicate = new JobCompanyNameContainsKeywordsPredicate(Arrays.asList("Mr", "Bean"));
        assertFalse(predicate.test(new JobBuilder().withCompanyName("Bean").build()));
    }
}
