package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BookingBuilder;

public class BookingMatchesEndDatePredicateTest {


    @Test
    public void equals() {
        LocalDate firstEndDate = LocalDate.of(2020, 10, 20);
        LocalDate secondEndDate = LocalDate.of(2020, 10, 25);
        BookingMatchesEndDatePredicate firstPredicate = new BookingMatchesEndDatePredicate(firstEndDate);
        BookingMatchesEndDatePredicate secondPredicate = new BookingMatchesEndDatePredicate(secondEndDate);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookingMatchesEndDatePredicate firstPredicateCopy = new BookingMatchesEndDatePredicate(firstEndDate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_bookingMatchesStartDate_returnsTrue() {
        LocalDate firstEndDate = LocalDate.of(2020, 10, 20);

        BookingMatchesEndDatePredicate predicate = new BookingMatchesEndDatePredicate(firstEndDate);
        assertTrue(predicate.test(new BookingBuilder().withEndDate(firstEndDate).build()));
    }

    @Test
    public void test_bookingDoesNotMatchStartDate_returnsFalse() {
        LocalDate firstEndDate = LocalDate.of(2020, 10, 20);
        LocalDate secondEndDate = LocalDate.of(2020, 10, 25);

        BookingMatchesEndDatePredicate predicate = new BookingMatchesEndDatePredicate(firstEndDate);
        assertFalse(predicate.test(new BookingBuilder().withEndDate(secondEndDate).build()));

    }
}
