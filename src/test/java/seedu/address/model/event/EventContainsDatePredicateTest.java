package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.event.EventBuilder;

public class EventContainsDatePredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("2020-02-01T12:00");
        List<String> secondPredicateKeywordList = Arrays.asList("2020-02-11T11:00", "2020-03-01T12:00");

        EventContainsDatePredicate firstPredicate = new EventContainsDatePredicate(firstPredicateKeywordList);
        EventContainsDatePredicate secondPredicate = new EventContainsDatePredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EventContainsDatePredicate firstPredicateCopy = new EventContainsDatePredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals("2020-02-05T12:00"));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_eventDateContainsKeywords_returnsTrue() {
        // One keyword
        EventContainsDatePredicate predicate =
                new EventContainsDatePredicate(Collections.singletonList("2020-02-01T12:00"));
        assertTrue(predicate.test(new EventBuilder().withName("Homework").withDate("1-2-2020 1200").build()));
    }

    @Test
    public void test_eventDateDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EventContainsDatePredicate predicate = new EventContainsDatePredicate(Collections.emptyList());
        assertFalse(predicate.test(new EventBuilder().withName("test").withDate("1-2-2020 1200").build()));

        // Non-matching keyword
        predicate = new EventContainsDatePredicate(Arrays.asList("2020-03-01T12:00"));
        assertFalse(predicate.test(new EventBuilder().withName("homework").withDate("1-2-2020 1200").build()));
    }
}
