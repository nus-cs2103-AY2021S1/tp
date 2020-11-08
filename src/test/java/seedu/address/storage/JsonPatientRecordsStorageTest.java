package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.HOON;
import static seedu.address.testutil.TypicalPatients.IDA;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientRecords;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.PatientRecords;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.patient.Patient;
import seedu.address.storage.patient.JsonPatientRecordsStorage;


//@@author AB3
public class JsonPatientRecordsStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPatientRecordsStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readCovigentApp_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCovigentApp(null));
    }

    private java.util.Optional<ReadOnlyList<Patient>> readCovigentApp(String filePath) throws Exception {
        return new JsonPatientRecordsStorage(Paths.get(filePath))
                .readPatientRecords(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCovigentApp("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readCovigentApp("notJsonFormatPatientRecords.json"));
    }

    @Test
    public void readCovigentApp_invalidPatientCovigentApp_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCovigentApp("invalidPatientPatientRecords.json"));
    }

    @Test
    public void readCovigentApp_invalidAndValidPatientCovigentApp_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCovigentApp("invalidAndValidPatientPatientRecords.json"));
    }

    @Test
    public void readAndSaveCovigentApp_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempCovigentApp.json");
        PatientRecords original = getTypicalPatientRecords();
        JsonPatientRecordsStorage jsonPatientRecordsStorage = new JsonPatientRecordsStorage(filePath);

        // Save in new file and read back
        jsonPatientRecordsStorage.savePatientRecords(original, filePath);
        ReadOnlyList<Patient> readBack = jsonPatientRecordsStorage.readPatientRecords(filePath).get();
        assertEquals(original, new PatientRecords(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPatient(HOON);
        original.removePatient(ALICE);
        jsonPatientRecordsStorage.savePatientRecords(original, filePath);
        readBack = jsonPatientRecordsStorage.readPatientRecords(filePath).get();
        assertEquals(original, new PatientRecords(readBack));

        // Save and read without specifying file path
        original.addPatient(IDA);
        jsonPatientRecordsStorage.savePatientRecords(original); // file path not specified
        readBack = jsonPatientRecordsStorage.readPatientRecords().get(); // file path not specified
        assertEquals(original, new PatientRecords(readBack));

    }

    @Test
    public void saveCovigentApp_nullCovigentApp_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCovigentApp(null, "SomeFile.json"));
    }

    /**
     * Saves {@code covigentApp} at the specified {@code filePath}.
     */
    private void saveCovigentApp(ReadOnlyList<Patient> covigentApp, String filePath) {
        try {
            new JsonPatientRecordsStorage(Paths.get(filePath))
                    .savePatientRecords(covigentApp, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCovigentApp_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCovigentApp(new PatientRecords(), null));
    }
}
