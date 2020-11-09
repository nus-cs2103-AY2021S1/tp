package seedu.address.storage.meeting;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyMeetingBook;
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
     * Returns ReadOnlyMeetingBook data as a {@link ReadOnlyMeetingBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMeetingBook> readMeetingBook() throws DataConversionException, IOException;

    /**
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     * @see #getMeetingBookFilePath()
     */
    Optional<ReadOnlyMeetingBook> readMeetingBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMeetingBook} to the storage.
     *
     * @param meetingManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMeetingBook(ReadOnlyMeetingBook meetingManager) throws IOException;

    /**
     *
     * @param meetingManager
     * @param filePath
     * @throws IOException If there was any problem when saving from the storage.
     */
    void saveMeetingBook(ReadOnlyMeetingBook meetingManager, Path filePath) throws IOException;

}
