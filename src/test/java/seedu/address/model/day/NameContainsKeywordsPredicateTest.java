package seedu.address.model.day;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.NameContainsKeywordsPredicate;
//import seedu.address.testutil.DayBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("010120");
        List<String> secondPredicateKeywordList = Arrays.asList("010120", "020120");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different day -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    //    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        //        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(
        //                Collections.singletonList("2020-06-09"));
        //        assertTrue(predicate.test(new DayBuilder().withDate("2020-06-09").build()));
        //
        //        // Multiple keywords
        //        predicate = new NameContainsKeywordsPredicate(Arrays.asList("2020-06-09", "2020-07-09"));
        //        assertTrue(predicate.test(new DayBuilder().withDate("2020-06-09").build()));
        //
        //        // Only one matching keyword
        //        predicate = new NameContainsKeywordsPredicate(Arrays.asList("2020-07-09", "2020-07-09"));
        //        assertTrue(predicate.test(new DayBuilder().withDate("2020-07-09").build()));

        //        // Mixed-case keywords
        //        predicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        //        assertTrue(predicate.test(new DayBuilder().withDate("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        //        // Zero keywords
        //        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        //        assertFalse(predicate.test(new DayBuilder().withDate("2020-06-09").build()));
        //
        //        // Non-matching keyword
        //        predicate = new NameContainsKeywordsPredicate(Arrays.asList("2020-06-09"));
        //        assertFalse(predicate.test(new DayBuilder().withDate("2020-07-09").build()));
        //
        //        // Keywords match phone, email and address, but does not match name
        //        predicate = new NameContainsKeywordsPredicate
        //        (Arrays.asList("42", "alice@email.com", "Main", "Street"));
        //        assertFalse(predicate.test(new DayBuilder().withDate("2020-06-09").withWeight("42")
        //              .build()));
    }
}
