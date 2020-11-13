package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.reminder.ReadOnlyReminders;

/**
 * Represents a storage for {@link seedu.address.model.RemindersImpl}.
 */
public interface RemindersStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getRemindersFilePath();

    /**
     * Returns Reminders data as a {@link ReadOnlyReminders}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyReminders> readReminders() throws DataConversionException, IOException;

    /**
     * @see #getRemindersFilePath
     */
    Optional<ReadOnlyReminders> readReminders(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyReminders} to the storage.
     * @param reminders cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveReminders(ReadOnlyReminders reminders) throws IOException;

    /**
     * @see #saveReminders(ReadOnlyReminders)
     */
    void saveReminders(ReadOnlyReminders reminders, Path filePath) throws IOException;

}
