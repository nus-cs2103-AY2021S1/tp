package seedu.medibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.storage.JsonMedicalNoteListStorage.NAME_DIR;
import static seedu.medibook.storage.JsonMedicalNoteListStorage.NAME_EXTENSION;
import static seedu.medibook.testutil.Assert.assertThrows;
import static seedu.medibook.testutil.TypicalMedicalNotes.VALID_MEDICAL_NOTE1;
import static seedu.medibook.testutil.TypicalMedicalNotes.VALID_MEDICAL_NOTE2;
import static seedu.medibook.testutil.TypicalMedicalNotes.getTypicalMedicalNoteList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.commons.util.FileUtil;
import seedu.medibook.model.medicalnote.MedicalNoteList;
import seedu.medibook.model.medicalnote.ReadOnlyMedicalNoteList;
import seedu.medibook.model.patient.Ic;

public class JsonMedicalNoteListStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonMedicalNoteListStorageTest");
    private static final Ic IC = new Ic("S7329204G");

    @TempDir
    public Path testFolder;

    @Test
    public void readMedicalNoteList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMedicalNoteList(null, IC));
    }

    private Optional<ReadOnlyMedicalNoteList> readMedicalNoteList(String filePath, Ic ic) throws Exception {
        return new JsonMedicalNoteListStorage(addToTestDataPathIfNotNull(filePath))
                .readMedicalNoteList(addToTestDataPathIfNotNull(filePath), ic);
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readMedicalNoteList("NonExistentFile.json", IC).isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readMedicalNoteList("notJsonFormatMedicalNotes", IC));
    }

    @Test
    public void readMedicalNoteList_invalidPatientMedicalNotes_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMedicalNoteList("invalidPatientMedicalNotes", IC));
    }

    @Test
    public void readMedicalNoteList_invalidAndValidPatientMedicalNotes_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readMedicalNoteList("invalidAndValidPatientMedicalNotes", IC));
    }

    @Test
    public void readAndSaveMedicalNoteList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempMedicalNotes");
        MedicalNoteList original = getTypicalMedicalNoteList();
        JsonMedicalNoteListStorage jsonMedicalNoteListStorage = new JsonMedicalNoteListStorage(filePath);

        // Save in new file and read back
        jsonMedicalNoteListStorage.saveMedicalNoteList(original, filePath, IC);
        ReadOnlyMedicalNoteList readBack = jsonMedicalNoteListStorage.readMedicalNoteList(filePath, IC).get();
        assertEquals(original, new MedicalNoteList(readBack.getMedicalNoteList()));

        // Modify data, overwrite exiting file, and read back
        original.add(VALID_MEDICAL_NOTE1);
        jsonMedicalNoteListStorage.saveMedicalNoteList(original, filePath, IC);
        readBack = jsonMedicalNoteListStorage.readMedicalNoteList(filePath, IC).get();
        assertEquals(original, new MedicalNoteList(readBack.getMedicalNoteList()));

        // Save and read without specifying file path
        original.add(VALID_MEDICAL_NOTE2);
        jsonMedicalNoteListStorage.saveMedicalNoteList(original, IC); // file path not specified
        readBack = jsonMedicalNoteListStorage.readMedicalNoteList(IC).get(); // file path not specified
        assertEquals(original, new MedicalNoteList(readBack.getMedicalNoteList()));
    }

    @Test
    public void saveMedicalNoteList_nullMMedicalNoteList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMedicalNoteList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code medicalNoteList} at the specified {@code filePath}.
     */
    private void saveMedicalNoteList(ReadOnlyMedicalNoteList medicalNoteList, String filePath) {
        try {
            new JsonMedicalNoteListStorage(addToTestDataPathIfNotNull(filePath))
                    .saveMedicalNoteList(medicalNoteList, addToTestDataPathIfNotNull(filePath), IC);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveMedicalNoteList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMedicalNoteList(new MedicalNoteList(), null));
    }

    @Test
    public void saveAndDeleteMedicalNoteList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempMedicalNotes");
        MedicalNoteList original = getTypicalMedicalNoteList();
        JsonMedicalNoteListStorage jsonMedicalNoteListStorage = new JsonMedicalNoteListStorage(filePath);

        // Save in new file and check if file exists
        jsonMedicalNoteListStorage.saveMedicalNoteList(original, filePath, IC);
        assertTrue(FileUtil.isFileExists(filePath.resolve(NAME_DIR).resolve(IC + NAME_EXTENSION)));

        // Delete the new file created
        jsonMedicalNoteListStorage.deleteMedicalNoteList(IC);
        assertFalse(FileUtil.isFileExists(filePath.resolve(NAME_DIR).resolve(IC + NAME_EXTENSION)));
    }

    @Test
    public void saveAndDeleteAllMedicalNoteList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempMedicalNotes");
        MedicalNoteList original = getTypicalMedicalNoteList();
        JsonMedicalNoteListStorage jsonMedicalNoteListStorage = new JsonMedicalNoteListStorage(filePath);

        // Save in new file and check if file exists
        Ic ic1 = new Ic("T6837664L");
        jsonMedicalNoteListStorage.saveMedicalNoteList(original, filePath, ic1);
        assertTrue(FileUtil.isFileExists(filePath.resolve(NAME_DIR).resolve(ic1 + NAME_EXTENSION)));

        Ic ic2 = new Ic("S9674263W");
        jsonMedicalNoteListStorage.saveMedicalNoteList(original, filePath, ic2);
        assertTrue(FileUtil.isFileExists(filePath.resolve(NAME_DIR).resolve(ic2 + NAME_EXTENSION)));

        Ic ic3 = new Ic("S6848336I");
        jsonMedicalNoteListStorage.saveMedicalNoteList(original, filePath, ic3);
        assertTrue(FileUtil.isFileExists(filePath.resolve(NAME_DIR).resolve(ic3 + NAME_EXTENSION)));

        // Delete the new files created
        jsonMedicalNoteListStorage.deleteAllMedicalNoteList();
        assertFalse(FileUtil.isFileExists(filePath.resolve(NAME_DIR).resolve(ic1 + NAME_EXTENSION)));
        assertFalse(FileUtil.isFileExists(filePath.resolve(NAME_DIR).resolve(ic2 + NAME_EXTENSION)));
        assertFalse(FileUtil.isFileExists(filePath.resolve(NAME_DIR).resolve(ic3 + NAME_EXTENSION)));
    }

    @Test
    public void saveAndRenameMedicalNoteList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempMedicalNotes");
        MedicalNoteList original = getTypicalMedicalNoteList();
        JsonMedicalNoteListStorage jsonMedicalNoteListStorage = new JsonMedicalNoteListStorage(filePath);

        // Save in new file and check if file exists
        jsonMedicalNoteListStorage.saveMedicalNoteList(original, filePath, IC);
        Path pathToOldFile = filePath.resolve(NAME_DIR).resolve(IC + NAME_EXTENSION);
        assertTrue(FileUtil.isFileExists(pathToOldFile));

        // Rename the file that was created
        Ic newIc = new Ic("T3240942K");
        jsonMedicalNoteListStorage.renameMedicalNoteList(IC, newIc);
        assertFalse(FileUtil.isFileExists(pathToOldFile));

        Path pathToNewFile = filePath.resolve(NAME_DIR).resolve(newIc + NAME_EXTENSION);
        assertTrue(FileUtil.isFileExists(pathToNewFile));
    }
}
