package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonUrlLinkContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("http://");
        List<String> secondPredicateKeywordList = Arrays.asList("http://faacebook.com");

        PersonUrlLinkContainsKeywordsPredicate firstPredicate =
                new PersonUrlLinkContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonUrlLinkContainsKeywordsPredicate secondPredicate =
                new PersonUrlLinkContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonUrlLinkContainsKeywordsPredicate firstPredicateCopy =
                new PersonUrlLinkContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different link -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsKeyword_returnsTrue() {
        // Exactly matching keyword
        PersonUrlLinkContainsKeywordsPredicate predicate =
                new PersonUrlLinkContainsKeywordsPredicate(Collections.singletonList("http://facebook.com"));
        assertTrue(predicate.test(new PersonBuilder().withUrlLink("http://facebook.com").build()));

        // Contains matching keyword
        predicate = new PersonUrlLinkContainsKeywordsPredicate(Collections.singletonList("linkedin.com"));
        assertTrue(predicate.test(new PersonBuilder().withUrlLink("linkedin.com/saralee").build()));

        // Mixed-case keyword
        predicate = new PersonUrlLinkContainsKeywordsPredicate(Collections.singletonList("LinkedIn.com"));
        assertTrue(predicate.test(new PersonBuilder().withUrlLink("http://linkedin.com/saralee").build()));

        // Zero keywords
        predicate = new PersonUrlLinkContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new PersonBuilder().withUrlLink("http://facebook.com").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        PersonUrlLinkContainsKeywordsPredicate predicate =
                new PersonUrlLinkContainsKeywordsPredicate(Collections.singletonList("linkedin.com/saralee"));
        assertFalse(predicate.test(new PersonBuilder().withUrlLink("http://linkedin.com/saratoh").build()));
        assertFalse(predicate.test(new PersonBuilder().withUrlLink("http://linkedin.com").build()));
    }
}
