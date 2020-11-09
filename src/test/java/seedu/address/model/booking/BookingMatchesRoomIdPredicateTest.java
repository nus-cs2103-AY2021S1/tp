package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BookingBuilder;

public class BookingMatchesRoomIdPredicateTest {

    @Test
    public void equals() {
        Integer firstPredicateRoomId = 1235;
        Integer secondPredicateRoomId = 1236;

        BookingMatchesRoomIdPredicate firstPredicate = new BookingMatchesRoomIdPredicate(firstPredicateRoomId);
        BookingMatchesRoomIdPredicate secondPredicate = new BookingMatchesRoomIdPredicate(secondPredicateRoomId);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookingMatchesRoomIdPredicate firstPredicateCopy = new BookingMatchesRoomIdPredicate(firstPredicateRoomId);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_bookingMatchesRoomId_returnsTrue() {
        // One keyword
        BookingMatchesRoomIdPredicate predicate = new BookingMatchesRoomIdPredicate(2103);
        assertTrue(predicate.test(new BookingBuilder().withRoomId(2103).build()));
    }

    @Test
    public void test_bookingDoesNotMatchRoomId_returnsFalse() {
        // Zero keywords
        BookingMatchesRoomIdPredicate predicate = new BookingMatchesRoomIdPredicate(2104);
        assertFalse(predicate.test(new BookingBuilder().withRoomId(2103).build()));

    }
}
