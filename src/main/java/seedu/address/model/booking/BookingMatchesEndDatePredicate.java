package seedu.address.model.booking;

import java.time.LocalDate;
import java.util.function.Predicate;


/**
 * Tests that a {@code Booking}'s {@code endDate} matches any of the keywords given.
 */
public class BookingMatchesEndDatePredicate implements Predicate<Booking> {
    private final LocalDate endDate;

    public BookingMatchesEndDatePredicate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean test(Booking booking) {
        return endDate.equals(booking.getEndDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookingMatchesEndDatePredicate // instanceof handles nulls
                && endDate.equals(((BookingMatchesEndDatePredicate) other).endDate)); // state check
    }

}
