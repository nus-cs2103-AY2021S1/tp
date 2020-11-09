package seedu.address.model.booking;

import java.util.function.Predicate;


/**
 * Tests that a {@code Booking}'s {@code roomId} matches any of the keywords given.
 */
public class BookingMatchesIsActivePredicate implements Predicate<Booking> {
    private final boolean isActive;

    public BookingMatchesIsActivePredicate(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean test(Booking booking) {
        return isActive == booking.isActive();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookingMatchesIsActivePredicate // instanceof handles nulls
                && isActive == ((BookingMatchesIsActivePredicate) other).isActive); // state check
    }

}
