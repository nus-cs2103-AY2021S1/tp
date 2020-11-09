package seedu.address.model.booking;

import java.util.function.Predicate;

public class BookingMatchesBookingIdPredicate implements Predicate<Booking> {
    private final Integer bookingId;

    public BookingMatchesBookingIdPredicate(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public boolean test(Booking booking) {
        return bookingId.equals(booking.getId());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookingMatchesBookingIdPredicate // instanceof handles nulls
                && bookingId.equals(((BookingMatchesBookingIdPredicate) other).bookingId)); // state check
    }
}
