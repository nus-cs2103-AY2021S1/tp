package seedu.address.storage.patient;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.patient.Patient;

//@@author
/**
 * A class to access patient records data stored as a json file on the hard disk.
 */
public class JsonPatientRecordsStorage implements PatientRecordsStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPatientRecordsStorage.class);

    private Path filePath;

    public JsonPatientRecordsStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPatientRecordsFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyList<Patient>> readPatientRecords()
            throws DataConversionException {
        return readPatientRecords(filePath);
    }

    /**
     * Similar to {@link #readPatientRecords()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyList<Patient>> readPatientRecords(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePatientRecords> jsonPatientRecords = JsonUtil.readJsonFile(
                filePath, JsonSerializablePatientRecords.class);
        if (!jsonPatientRecords.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPatientRecords.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePatientRecords(ReadOnlyList<Patient> patientRecords) throws IOException {
        savePatientRecords(patientRecords, filePath);
    }

    /**
     * Similar to {@code #savePatientRecords(ReadOnlyList<Patient>)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePatientRecords(ReadOnlyList<Patient> patientRecords, Path filePath) throws IOException {
        requireNonNull(patientRecords);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePatientRecords(patientRecords), filePath);
    }

}
