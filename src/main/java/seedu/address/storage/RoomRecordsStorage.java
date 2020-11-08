package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.room.Room;

//@@author itssodium
public interface RoomRecordsStorage {

    Path getRoomsOccupied();

    Optional<ReadOnlyList<Room>> readOnlyRoomOccupancy() throws DataConversionException;

    Optional<ReadOnlyList<Room>> readOnlyRoomOccupancy(Path filePath) throws DataConversionException;

    void saveOccupiedRooms(ReadOnlyList<Room> roomList) throws IOException;

    void saveOccupiedRooms(ReadOnlyList<Room> roomList, Path fileRoomsOccupied) throws IOException;
}
