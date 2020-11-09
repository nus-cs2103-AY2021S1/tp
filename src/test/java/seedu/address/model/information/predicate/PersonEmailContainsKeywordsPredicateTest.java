package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonEmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("@gmail.com");
        List<String> secondPredicateKeywordList = Arrays.asList("joan@gmail.com");

        PersonEmailContainsKeywordsPredicate firstPredicate =
                new PersonEmailContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonEmailContainsKeywordsPredicate secondPredicate =
                new PersonEmailContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonEmailContainsKeywordsPredicate firstPredicateCopy =
                new PersonEmailContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different email -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeyword_returnsTrue() {
        // Exactly matching keyword
        PersonEmailContainsKeywordsPredicate predicate =
                new PersonEmailContainsKeywordsPredicate(Collections.singletonList("joan@gmail.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("joan@gmail.com").build()));

        // Contains matching keyword
        predicate = new PersonEmailContainsKeywordsPredicate(Collections.singletonList("@gmail.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("joan@gmail.com").build()));

        // Mixed-case keyword
        predicate = new PersonEmailContainsKeywordsPredicate(Collections.singletonList("JoAN@GmaiL.CoM"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("joan@gmail.com").build()));

        // Zero keywords
        predicate = new PersonEmailContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new PersonBuilder().withEmail("joan@gmail.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        PersonEmailContainsKeywordsPredicate predicate =
                new PersonEmailContainsKeywordsPredicate(Collections.singletonList("@gmail.sg"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("joan@gmail.com").build()));
    }
}
