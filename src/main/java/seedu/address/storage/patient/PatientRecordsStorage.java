package seedu.address.storage.patient;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.PatientRecords;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.patient.Patient;

//@@author AB3
/**
 * Represents a storage for {@link PatientRecords}.
 */
public interface PatientRecordsStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPatientRecordsFilePath();

    /**
     * Returns CovigentApp data as a {@code ReadOnlyList<Patient>}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyList<Patient>> readPatientRecords() throws DataConversionException, IOException;

    /**
     * @see #getPatientRecordsFilePath()
     */
    Optional<ReadOnlyList<Patient>> readPatientRecords(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@code ReadOnlyList<Patient>} to the storage.
     * @param patientRecords cannot be null.
     *
     * @throws IOException if there was any problem writing to the file.
     */
    void savePatientRecords(ReadOnlyList<Patient> patientRecords) throws IOException;

    /**
     * @see #savePatientRecords(ReadOnlyList)
     */
    void savePatientRecords(ReadOnlyList<Patient> patientRecords, Path filePath) throws IOException;

}
