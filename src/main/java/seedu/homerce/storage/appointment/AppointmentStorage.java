package seedu.homerce.storage.appointment;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.homerce.commons.exceptions.DataConversionException;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ReadOnlyAppointmentManager;

/**
 * Represents a storage for {@link AppointmentManager}.
 */
public interface AppointmentStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAppointmentManagerStorageFilePath();

    /**
     * Returns AppointmentManager data as a {@link ReadOnlyAppointmentManager}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAppointmentManager> readAppointmentManager() throws DataConversionException, IOException;

    /**
     * @see #readAppointmentManager()
     */
    Optional<ReadOnlyAppointmentManager> readAppointmentManager(Path filePath) throws DataConversionException,
            IOException;

    /**
     * Saves the given {@link ReadOnlyAppointmentManager} to the storage.
     *
     * @param appointmentManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAppointmentManager(ReadOnlyAppointmentManager appointmentManager) throws IOException;

    /**
     * @see #saveAppointmentManager(ReadOnlyAppointmentManager)
     */
    void saveAppointmentManager(ReadOnlyAppointmentManager appointmentManager, Path filePath) throws IOException;
}
