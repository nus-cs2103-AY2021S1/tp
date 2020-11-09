package seedu.address.testutil;

import seedu.address.model.roomservice.RoomService;
import seedu.address.model.roomservice.RoomServiceType;

/**
 * A utility class to help with building RoomService objects.
 */
public class RoomServiceBuilder {
    public static final int DEFAULT_BOOKING_ID = 1;
    public static final RoomServiceType DEFAULT_TYPE = RoomServiceType.DINING;

    private int bookingId;
    private RoomServiceType type;

    /**
     * Default Constructor for RoomServiceBuilder.
     */
    public RoomServiceBuilder() {
        this.bookingId = DEFAULT_BOOKING_ID;
        this.type = DEFAULT_TYPE;
    }

    /**
     * Constructs a {@code RoomServiceBuilder}
     * @param tobeCopied a valid RoomService.
     */
    public RoomServiceBuilder(RoomService tobeCopied) {
        this.bookingId = tobeCopied.getBookingId();
        this.type = tobeCopied.getType();
    }

    /**
     * Sets RoomServiceBuilder with new bookingId.
     */
    public RoomServiceBuilder withBookingId(int bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    /**
     * Sets RoomServiceBuilder with new type
     */
    public RoomServiceBuilder withType(RoomServiceType type) {
        this.type = type;
        return this;
    }

    /**
     * Creates a RoomService object from the current fields.
     */
    public RoomService build() {
        return new RoomService(bookingId, type);
    }
}
