package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.JobBuilder;

public class JobVacancyContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("9");
        List<String> secondPredicateKeywordList = Arrays.asList("99");

        JobVacancyContainsKeywordsPredicate firstPredicate =
                new JobVacancyContainsKeywordsPredicate(firstPredicateKeywordList);
        JobVacancyContainsKeywordsPredicate secondPredicate =
                new JobVacancyContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        JobVacancyContainsKeywordsPredicate firstPredicateCopy =
                new JobVacancyContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different vacancy -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_vacancyContainsKeyword_returnsTrue() {
        // Exactly matching keyword
        JobVacancyContainsKeywordsPredicate predicate =
                new JobVacancyContainsKeywordsPredicate(Collections.singletonList("15"));
        assertTrue(predicate.test(new JobBuilder().withVacancy("15").build()));

        // Zero keywords
        predicate = new JobVacancyContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new JobBuilder().withVacancy("10").build()));
    }

    @Test
    public void test_vacancyDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        JobVacancyContainsKeywordsPredicate predicate =
                new JobVacancyContainsKeywordsPredicate(Collections.singletonList("9"));
        assertFalse(predicate.test(new JobBuilder().withVacancy("99").build()));
        assertFalse(predicate.test(new JobBuilder().withVacancy("19").build()));
    }
}
