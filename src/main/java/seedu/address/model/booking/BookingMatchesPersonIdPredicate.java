package seedu.address.model.booking;

import java.util.function.Predicate;


/**
 * Tests that a {@code Booking}'s {@code personId} matches any of the keywords given.
 */
public class BookingMatchesPersonIdPredicate implements Predicate<Booking> {
    private final Integer personId;

    public BookingMatchesPersonIdPredicate(Integer personId) {
        this.personId = personId;
    }

    @Override
    public boolean test(Booking booking) {
        return personId.equals(booking.getPersonId());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookingMatchesPersonIdPredicate // instanceof handles nulls
                && personId.equals(((BookingMatchesPersonIdPredicate) other).personId)); // state check
    }

}
