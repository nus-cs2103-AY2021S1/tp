package seedu.address.model.roomservice;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Class for different types of room service
 */
public class RoomService {

    // Identity fields
    private final Integer bookingId;
    private final RoomServiceType type;

    /**
     * Instantiates room service with booking id and type
     */
    public RoomService(Integer bookingId, RoomServiceType type) {
        requireAllNonNull(bookingId, type);
        this.bookingId = bookingId;
        this.type = type;
    }

    public Integer getBookingId() {
        return this.bookingId;
    }

    /**
     * Checks whether this room service belongs to the booking with bookingId
     */
    public boolean belongsToBooking(Integer bookingId) {
        assert bookingId >= 0;
        return this.getBookingId().equals(bookingId);
    }

    public RoomServiceType getType() {
        return this.type;
    }

    /**
     * Returns true if both room service have the same booking id
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RoomService)) {
            return false;
        }

        RoomService otherRoomService = (RoomService) other;
        return otherRoomService.getBookingId().equals(getBookingId())
                && otherRoomService.type == type;
    }

    @Override
    public String toString() {
        return String.format("[BookingID: %s; Service type: %s; Price: $%s]", this.bookingId,
                this.type.getVerboseName(), this.type.getPrice());
    }
}
