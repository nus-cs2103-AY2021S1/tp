package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonExperienceContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("5");
        List<String> secondPredicateKeywordList = Arrays.asList("50");

        PersonExperienceContainsKeywordsPredicate firstPredicate =
                new PersonExperienceContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonExperienceContainsKeywordsPredicate secondPredicate =
                new PersonExperienceContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonExperienceContainsKeywordsPredicate firstPredicateCopy =
                new PersonExperienceContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different experience -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_experienceContainsKeyword_returnsTrue() {
        // Exactly matching keyword
        PersonExperienceContainsKeywordsPredicate predicate =
                new PersonExperienceContainsKeywordsPredicate(Collections.singletonList("5"));
        assertTrue(predicate.test(new PersonBuilder().withExperience("5").build()));

        // Zero keywords
        predicate = new PersonExperienceContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new PersonBuilder().withExperience("5").build()));
    }

    @Test
    public void test_experienceDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        PersonExperienceContainsKeywordsPredicate predicate =
                new PersonExperienceContainsKeywordsPredicate(Collections.singletonList("5"));
        assertFalse(predicate.test(new PersonBuilder().withExperience("15").build()));
    }
}
