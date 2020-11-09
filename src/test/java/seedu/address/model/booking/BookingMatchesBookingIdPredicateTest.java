package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BookingBuilder;

public class BookingMatchesBookingIdPredicateTest {

    @Test
    public void equals() {
        Integer firstPredicateRoomId = 1;
        Integer secondPredicateRoomId = 2;

        BookingMatchesBookingIdPredicate firstPredicate = new BookingMatchesBookingIdPredicate(firstPredicateRoomId);
        BookingMatchesBookingIdPredicate secondPredicate = new BookingMatchesBookingIdPredicate(secondPredicateRoomId);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookingMatchesBookingIdPredicate firstPredicateCopy =
                new BookingMatchesBookingIdPredicate(firstPredicateRoomId);

        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_bookingMatchesBookingIdPredicate_returnsTrue() {
        BookingMatchesBookingIdPredicate predicate = new BookingMatchesBookingIdPredicate(1);
        assertTrue(predicate.test(new BookingBuilder().withId(1).build()));
    }

    @Test
    public void test_bookingDoesNotMatchBookingId_returnsFalse() {
        BookingMatchesBookingIdPredicate predicate = new BookingMatchesBookingIdPredicate(2);
        assertFalse(predicate.test(new BookingBuilder().withId(1).build()));

    }
}
