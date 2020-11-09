package seedu.address.model.booking;

import java.time.LocalDate;
import java.util.function.Predicate;


/**
 * Tests that a {@code Booking}'s {@code startDate} matches any of the keywords given.
 */
public class BookingMatchesStartDatePredicate implements Predicate<Booking> {
    private final LocalDate startDate;

    public BookingMatchesStartDatePredicate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean test(Booking booking) {
        return startDate.equals(booking.getStartDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookingMatchesStartDatePredicate // instanceof handles nulls
                && startDate.equals(((BookingMatchesStartDatePredicate) other).startDate)); // state check
    }

}
