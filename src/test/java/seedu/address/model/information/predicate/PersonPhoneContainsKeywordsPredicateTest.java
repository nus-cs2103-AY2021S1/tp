package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonPhoneContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("9191");
        List<String> secondPredicateKeywordList = Arrays.asList("91912020");

        PersonPhoneContainsKeywordsPredicate firstPredicate =
                new PersonPhoneContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonPhoneContainsKeywordsPredicate secondPredicate =
                new PersonPhoneContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonPhoneContainsKeywordsPredicate firstPredicateCopy =
                new PersonPhoneContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different phone number -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsKeyword_returnsTrue() {
        // Exactly matching keyword
        PersonPhoneContainsKeywordsPredicate predicate =
                new PersonPhoneContainsKeywordsPredicate(Collections.singletonList("91910202"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("91910202").build()));

        // Zero keywords
        predicate = new PersonPhoneContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new PersonBuilder().withPhone("91910202").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        PersonPhoneContainsKeywordsPredicate predicate =
                new PersonPhoneContainsKeywordsPredicate(Collections.singletonList("9191"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("91910000").build()));
        assertFalse(predicate.test(new PersonBuilder().withPhone("91000091").build()));
    }
}
