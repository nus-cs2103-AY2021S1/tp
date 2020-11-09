package seedu.address.model.booking;

import java.util.function.Predicate;


/**
 * Tests that a {@code Booking}'s {@code roomId} matches any of the keywords given.
 */
public class BookingMatchesRoomIdPredicate implements Predicate<Booking> {
    private final Integer roomId;

    public BookingMatchesRoomIdPredicate(Integer roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean test(Booking booking) {
        return roomId.equals(booking.getRoomId());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookingMatchesRoomIdPredicate // instanceof handles nulls
                && roomId.equals(((BookingMatchesRoomIdPredicate) other).roomId)); // state check
    }

}
