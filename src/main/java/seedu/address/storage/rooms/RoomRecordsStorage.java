package seedu.address.storage.rooms;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.room.Room;

//@@author itssodium
public interface RoomRecordsStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getRoomsRecordsFilePath();

    /**
     * Returns CovigentApp data as a {@code ReadOnlyList<Room>}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyList<Room>> readOnlyRoomOccupancy() throws DataConversionException, IOException;

    /**
     * @see #readOnlyRoomOccupancy()
     */
    Optional<ReadOnlyList<Room>> readOnlyRoomOccupancy(Path filePath) throws DataConversionException;

    /**
     * Saves the given {@code ReadOnlyList<Room>} to the storage.
     * @param roomList cannot be null.
     *
     * @throws IOException if there was any problem writing to the file.
     */
    void saveRoomsInformation(ReadOnlyList<Room> roomList) throws IOException;

    /**
     * @see #saveRoomsInformation(ReadOnlyList)
     */
    void saveRoomsInformation(ReadOnlyList<Room> roomList, Path fileRoomsOccupied) throws IOException;
}
