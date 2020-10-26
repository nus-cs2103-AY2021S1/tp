package seedu.address.storage.schedule;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.event.ReadOnlyEvent;
import seedu.address.model.event.Scheduler;

/**
 * Represents a storage for {@link Scheduler}.
 */
public interface ScheduleStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getScheduleFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyEvent}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyEvent> readSchedule() throws DataConversionException, IOException;

    /**
     * @see #getScheduleFilePath()
     */
    Optional<ReadOnlyEvent> readSchedule(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyEvent} to the storage.
     * @param schedule cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSchedule(ReadOnlyEvent schedule) throws IOException;

    /**
     * @see #saveSchedule(ReadOnlyEvent)
     */
    void saveSchedule(ReadOnlyEvent schedule, Path filePath) throws IOException;

}
