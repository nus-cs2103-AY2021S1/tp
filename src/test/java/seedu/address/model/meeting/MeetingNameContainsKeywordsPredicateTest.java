package seedu.address.model.meeting;

import org.junit.jupiter.api.Test;
import seedu.address.model.meeting.MeetingNameContainsKeywordsPredicate;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.PersonBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MeetingNameContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        MeetingNameContainsKeywordsPredicate firstPredicate =
                new MeetingNameContainsKeywordsPredicate(firstPredicateKeywordList);
        MeetingNameContainsKeywordsPredicate secondPredicate =
                new MeetingNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MeetingNameContainsKeywordsPredicate firstPredicateCopy =
                new MeetingNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_MeetingNameContainsKeywords_returnsTrue() {
        // One keyword
        MeetingNameContainsKeywordsPredicate predicate = new MeetingNameContainsKeywordsPredicate(
                Collections.singletonList("CS2100"));
        assertTrue(predicate.test(new MeetingBuilder().withName("CS2100 CS2101").build()));

        // Multiple keywords
        predicate = new MeetingNameContainsKeywordsPredicate(Arrays.asList("CS2100", "CS2101"));
        assertTrue(predicate.test(new MeetingBuilder().withName("CS2100 CS2101").build()));

        // Only one matching keyword
        predicate = new MeetingNameContainsKeywordsPredicate(Arrays.asList("CS2101", "CS2103"));
        assertTrue(predicate.test(new MeetingBuilder().withName("CS2100 CS2103").build()));

        // Mixed-case keywords
        predicate = new MeetingNameContainsKeywordsPredicate(Arrays.asList("cs2100", "cs2101"));
        assertTrue(predicate.test(new MeetingBuilder().withName("CS2100 CS2101").build()));
    }

    @Test
    public void test_MeetingNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        MeetingNameContainsKeywordsPredicate predicate = new MeetingNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().withName("CS2100").build()));

        // Non-matching keyword
        predicate = new MeetingNameContainsKeywordsPredicate(Arrays.asList("CS2102"));
        assertFalse(predicate.test(new MeetingBuilder().withName("CS2100 CS2101").build()));

        // Keywords match date and time, but does not match meeting name
        predicate = new MeetingNameContainsKeywordsPredicate(Arrays.asList("2020-10-03", "10:00"));
        assertFalse(predicate.test(new MeetingBuilder().withName("CS2100").withDate("2020-10-03")
                .withTime("10:00").build()));
    }
}
