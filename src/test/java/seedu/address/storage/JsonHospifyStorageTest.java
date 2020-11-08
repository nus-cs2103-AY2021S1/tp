package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.HOON;
import static seedu.address.testutil.TypicalPatients.IDA;
import static seedu.address.testutil.TypicalPatients.getTypicalHospifyBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.HospifyBook;
import seedu.address.model.ReadOnlyHospifyBook;

public class JsonHospifyStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyHospifyBook> readAddressBook(String filePath) throws Exception {
        return new JsonHospifyStorage(Paths.get(filePath)).readHospifyBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatHospify.json"));
    }

    @Test
    public void readAddressBook_invalidPatientHospify_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPatientHospify.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPatientHospify_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPatientHospify.json"));
    }

    @Test
    public void readAndSaveHospify_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempHospify.json");
        HospifyBook original = getTypicalHospifyBook();
        JsonHospifyStorage jsonHospifyStorage = new JsonHospifyStorage(filePath);

        // Save in new file and read back
        jsonHospifyStorage.saveHospifyBook(original, filePath);
        ReadOnlyHospifyBook readBack = jsonHospifyStorage.readHospifyBook(filePath).get();
        assertEquals(original, new HospifyBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPatient(HOON);
        original.removePatient(ALICE);
        jsonHospifyStorage.saveHospifyBook(original, filePath);
        readBack = jsonHospifyStorage.readHospifyBook(filePath).get();
        assertEquals(original, new HospifyBook(readBack));

        // Save and read without specifying file path
        original.addPatient(IDA);
        jsonHospifyStorage.saveHospifyBook(original); // file path not specified
        readBack = jsonHospifyStorage.readHospifyBook().get(); // file path not specified
        assertEquals(original, new HospifyBook(readBack));

    }

    @Test
    public void saveHospify_nullHospify_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHospify(null, "SomeFile.json"));
    }

    /**
     * Saves {@code hospifyBook} at the specified {@code filePath}.
     */
    private void saveHospify(ReadOnlyHospifyBook hospifyBook, String filePath) {
        try {
            new JsonHospifyStorage(Paths.get(filePath))
                    .saveHospifyBook(hospifyBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveHospify_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHospify(new HospifyBook(), null));
    }
}
