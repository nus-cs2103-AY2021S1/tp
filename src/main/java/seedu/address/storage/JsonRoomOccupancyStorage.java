package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.room.Room;

/**
 * Reads data from storage data files and imports them into RoomList
 */
//@@author itssodium
public class JsonRoomOccupancyStorage implements RoomRecordsStorage {
    private Path roomsOccupied;

    public JsonRoomOccupancyStorage() {}
    /**
     * Creates RoomOccupancyStorage object that reads the number of rooms a hotel has and the rooms which are
     * occupied
     */
    public JsonRoomOccupancyStorage(Path roomsOccupied) {
        this.roomsOccupied = roomsOccupied;
    }

    public Path getRoomsOccupied() {
        return roomsOccupied;
    }

    @Override
    public Optional<ReadOnlyList<Room>> readOnlyRoomOccupancy() throws DataConversionException {
        return readOnlyRoomOccupancy(roomsOccupied);
    }
    /**
     * Returns RoomList data as a {@code ReadOnlyList<Room>}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    @Override
    public Optional<ReadOnlyList<Room>> readOnlyRoomOccupancy(Path filePath) throws DataConversionException {
        Optional<JsonSerializableRoomList> jsonCovigentApp = JsonUtil.readJsonFile(
                filePath, JsonSerializableRoomList.class);
        if (!jsonCovigentApp.isPresent()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonCovigentApp.get().toModelType());
        } catch (IllegalValueException ive) {
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveOccupiedRooms(ReadOnlyList<Room> roomList) throws IOException {
        saveOccupiedRooms(roomList, roomsOccupied);
    }

    /**
     * Function saves the room numbers of occupied rooms
     *
     * @param roomList contains information of which rooms are occupied
     * @param fileRoomsOccupied Path to where to write the room numbers of occupied rooms
     */
    public void saveOccupiedRooms(ReadOnlyList<Room> roomList, Path fileRoomsOccupied) throws IOException {
        FileUtil.createIfMissing(fileRoomsOccupied);
        List<Room> rooms = new ArrayList<>();
        rooms.addAll(roomList.getReadOnlyList());
        JsonUtil.saveJsonFile(new JsonSerializableRoomList(roomList), fileRoomsOccupied);
    }
}
