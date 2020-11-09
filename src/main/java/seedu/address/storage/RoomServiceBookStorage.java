package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyPersonBook;
import seedu.address.model.ReadOnlyRoomServiceBook;

/**
 * Represents a storage for {@link seedu.address.model.RoomServiceBook}.
 */
public interface RoomServiceBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getRoomServiceBookFilePath();

    /**
     * Returns RoomServiceBook data as a {@link ReadOnlyPersonBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyRoomServiceBook> readRoomServiceBook() throws DataConversionException, IOException;

    /**
     * @see #getRoomServiceBookFilePath()
     */
    Optional<ReadOnlyRoomServiceBook> readRoomServiceBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyRoomServiceBook} to the storage.
     * @param roomServiceBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveRoomServiceBook(ReadOnlyRoomServiceBook roomServiceBook) throws IOException;

    /**
     * @see #saveRoomServiceBook(ReadOnlyRoomServiceBook)
     */
    void saveRoomServiceBook(ReadOnlyRoomServiceBook roomServiceBook, Path filePath) throws IOException;
}
