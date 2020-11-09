package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyRoomServiceBook;
import seedu.address.model.RoomServiceBook;
import seedu.address.model.roomservice.RoomService;

/**
 * An Immutable RoomServiceBook that is serializable to JSON format.
 */
@JsonRootName(value = "roomServiceBook")
public class JsonSerializableRoomServiceBook {

    private final List<JsonAdaptedRoomService> roomServices = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableRoomServiceBook} with the given bookings.
     */
    @JsonCreator
    public JsonSerializableRoomServiceBook(@JsonProperty("roomServices") List<JsonAdaptedRoomService> roomServices) {
        this.roomServices.addAll(roomServices);
    }

    /**
     * Converts a given {@code ReadOnlyRoomServiceBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableRoomServiceBook}.
     */
    public JsonSerializableRoomServiceBook(ReadOnlyRoomServiceBook source) {
        roomServices.addAll(source.getRoomServiceList().stream()
                .map(JsonAdaptedRoomService::new).collect(Collectors.toList()));
    }

    /**
     * Converts this room service book into the model's {@code RoomServiceBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public RoomServiceBook toModelType() throws IllegalValueException {
        RoomServiceBook roomServiceBook = new RoomServiceBook();
        for (JsonAdaptedRoomService jsonAdaptedRoomService : roomServices) {
            RoomService roomService = jsonAdaptedRoomService.toModelType();
            roomServiceBook.addRoomService(roomService);
        }
        return roomServiceBook;
    }

}
