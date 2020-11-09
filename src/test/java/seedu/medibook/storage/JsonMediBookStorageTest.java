package seedu.medibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.medibook.testutil.Assert.assertThrows;
import static seedu.medibook.testutil.TypicalPatients.ALICE;
import static seedu.medibook.testutil.TypicalPatients.HOON;
import static seedu.medibook.testutil.TypicalPatients.IDA;
import static seedu.medibook.testutil.TypicalPatients.getTypicalMediBook;
import static seedu.medibook.testutil.TypicalPatients.getTypicalMediBookWithAllEmptyMedicalNoteList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.model.MediBook;
import seedu.medibook.model.ReadOnlyMediBook;
import seedu.medibook.model.medicalnote.MedicalNoteList;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.testutil.PatientBuilder;

public class JsonMediBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonMediBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readMediBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMediBook(null));
    }

    private java.util.Optional<ReadOnlyMediBook> readMediBook(String filePath) throws Exception {
        return new JsonMediBookStorage(Paths.get(filePath)).readMediBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readMediBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readMediBook("notJsonFormatMediBook.json"));
    }

    @Test
    public void readMediBook_invalidPatientMediBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMediBook("invalidPatientMediBook.json"));
    }

    @Test
    public void readMediBook_invalidAndValidPatientMediBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMediBook("invalidAndValidPatientMediBook.json"));
    }

    @Test
    public void readAndSaveMediBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempMediBook.json");
        MediBook original = getTypicalMediBook();
        // This is the expected MediBook when program loads.
        MediBook originalWithAllEmptyMedicalNoteList = getTypicalMediBookWithAllEmptyMedicalNoteList();
        JsonMediBookStorage jsonMediBookStorage = new JsonMediBookStorage(filePath);

        // Save in new file and read back
        jsonMediBookStorage.saveMediBook(original, filePath);
        ReadOnlyMediBook readBack = jsonMediBookStorage.readMediBook(filePath).get();
        assertEquals(originalWithAllEmptyMedicalNoteList, new MediBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPatient(HOON);
        originalWithAllEmptyMedicalNoteList.addPatient(HOON);

        original.removePatient(ALICE);
        Patient copyOfAliceWithEmptyMedicalNoteList = new PatientBuilder(ALICE).build();
        copyOfAliceWithEmptyMedicalNoteList.setMedicalNoteList(new MedicalNoteList());
        originalWithAllEmptyMedicalNoteList.removePatient(copyOfAliceWithEmptyMedicalNoteList);

        jsonMediBookStorage.saveMediBook(original, filePath);
        readBack = jsonMediBookStorage.readMediBook(filePath).get();
        assertEquals(originalWithAllEmptyMedicalNoteList, new MediBook(readBack));

        // Save and read without specifying file path
        original.addPatient(IDA);
        originalWithAllEmptyMedicalNoteList.addPatient(IDA);

        jsonMediBookStorage.saveMediBook(original); // file path not specified
        readBack = jsonMediBookStorage.readMediBook().get(); // file path not specified
        assertEquals(originalWithAllEmptyMedicalNoteList, new MediBook(readBack));

    }

    @Test
    public void saveMediBook_nullMediBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMediBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code mediBook} at the specified {@code filePath}.
     */
    private void saveMediBook(ReadOnlyMediBook mediBook, String filePath) {
        try {
            new JsonMediBookStorage(Paths.get(filePath))
                    .saveMediBook(mediBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveMediBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMediBook(new MediBook(), null));
    }
}
