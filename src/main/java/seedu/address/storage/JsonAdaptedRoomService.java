package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.roomservice.RoomService;
import seedu.address.model.roomservice.RoomServiceType;

public class JsonAdaptedRoomService {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "RoomService's %s field is missing!";

    private final Integer bookingId;
    private final RoomServiceType type;

    /**
     * Constructs a {@code JsonAdaptedRoomService} with the given room service details.
     */
    @JsonCreator
    public JsonAdaptedRoomService(@JsonProperty("bookingId") Integer bookingId,
                                  @JsonProperty("type") RoomServiceType type) {
        this.bookingId = bookingId;
        this.type = type;
    }

    /**
     * Converts a given {@code RoomService} into this class for JSON use.
     */
    public JsonAdaptedRoomService(RoomService source) {
        bookingId = source.getBookingId();
        type = source.getType();
    }

    /**
     * Converts this JSON-friendly adapted booking object into the model's {@code RoomService} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted booking.
     */
    public RoomService toModelType() throws IllegalValueException {

        if (bookingId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "bookingId"));
        }

        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "type"));
        }

        return new RoomService(bookingId, type);
    }
}
