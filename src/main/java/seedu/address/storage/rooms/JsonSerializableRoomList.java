package seedu.address.storage.rooms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.RoomList;
import seedu.address.model.room.Room;

//@@author itssodium
@JsonRootName(value = "roomList")
public class JsonSerializableRoomList {

    public static final String WRONG_ORDER_OF_ROOM = "Rooms are not being input in the correct order.";
    @JsonProperty("rooms")
    private final List<JsonAdaptedRoom> rooms = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableRoomList} with the given rooms.
     */
    @JsonCreator
    public JsonSerializableRoomList(@JsonProperty("rooms") List<JsonAdaptedRoom> rooms) {
        this.rooms.addAll(rooms);
    }

    /**
     * Converts a given {@code RoomList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableRoomList}.
     */
    public JsonSerializableRoomList(ReadOnlyList<Room> source) {
        rooms.addAll(source.getReadOnlyList().stream().map(JsonAdaptedRoom::new).collect(Collectors.toList()));
    }

    /**
     * Converts this task list into the model's {@code RoomList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public RoomList toModelType() throws IllegalValueException {
        RoomList roomList = new RoomList();
        int currRoomNum = 1;
        for (JsonAdaptedRoom jsonAdaptedRoom : rooms) {
            Room room = jsonAdaptedRoom.toModelType();
            if (isNotInOrder(room, currRoomNum)) {
                throw new IllegalValueException(WRONG_ORDER_OF_ROOM);
            }
            currRoomNum++;
            roomList.addRooms(room);
        }
        return roomList;
    }

    private boolean isNotInOrder(Room room, int currRoomNum) {
        return room.getRoomNumber() != currRoomNum;
    }
}
