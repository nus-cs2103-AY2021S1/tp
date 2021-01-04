package seedu.homerce.storage.appointment;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.homerce.commons.core.LogsCenter;
import seedu.homerce.commons.exceptions.DataConversionException;
import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.commons.util.FileUtil;
import seedu.homerce.commons.util.JsonUtil;
import seedu.homerce.model.manager.ReadOnlyAppointmentManager;

/**
 * A class to access AppointmentManager data stored as a json file on the hard disk.
 */
public class JsonAppointmentStorage implements AppointmentStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAppointmentStorage.class);

    private Path filePath;

    public JsonAppointmentStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAppointmentManagerStorageFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAppointmentManager> readAppointmentManager() throws DataConversionException, IOException {
        return readAppointmentManager(filePath);
    }

    /**
     * Similar to {@link #readAppointmentManager()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyAppointmentManager> readAppointmentManager(Path filePath) throws DataConversionException,
            IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableAppointmentManager> jsonAppointmentManager = JsonUtil.readJsonFile(
                filePath, JsonSerializableAppointmentManager.class);
        if (!jsonAppointmentManager.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAppointmentManager.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAppointmentManager(ReadOnlyAppointmentManager appointmentManager) throws IOException {
        saveAppointmentManager(appointmentManager, filePath);
    }

    @Override
    public void saveAppointmentManager(ReadOnlyAppointmentManager appointmentManager,
                                       Path filePath) throws IOException {
        requireNonNull(appointmentManager);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAppointmentManager(appointmentManager), filePath);
    }
}
