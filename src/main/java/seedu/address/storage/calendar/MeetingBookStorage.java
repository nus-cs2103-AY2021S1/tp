package seedu.address.storage.calendar;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyMeetingManager;
import seedu.address.model.bidbook.BidBook;

/**
 * Represents a storage for {@link BidBook}.
 */
public interface MeetingBookStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getMeetingBookFilePath();

    /**
     * Returns ReadOnlyMeetingManager data as a {@link ReadOnlyMeetingManager}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMeetingManager> readMeetingBook() throws DataConversionException, IOException;

    /**
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     * @see #getMeetingBookFilePath()
     */
    Optional<ReadOnlyMeetingManager> readMeetingBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMeetingManager} to the storage.
     *
     * @param meetingManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMeetingBook(ReadOnlyMeetingManager meetingManager) throws IOException;

    /**
     *
     * @param meetingManager
     * @param filePath
     * @throws IOException If there was any problem when saving from the storage.
     */
    void saveMeetingBook(ReadOnlyMeetingManager meetingManager, Path filePath) throws IOException;

}
