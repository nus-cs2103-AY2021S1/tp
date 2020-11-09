package seedu.medibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.testutil.TypicalMedicalNotes.getTypicalMedicalNoteList;
import static seedu.medibook.testutil.TypicalPatients.getTypicalMediBook;
import static seedu.medibook.testutil.TypicalPatients.getTypicalMediBookWithAllEmptyMedicalNoteList;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.medibook.commons.core.GuiSettings;
import seedu.medibook.model.MediBook;
import seedu.medibook.model.ReadOnlyMediBook;
import seedu.medibook.model.UserPrefs;
import seedu.medibook.model.medicalnote.MedicalNoteList;
import seedu.medibook.model.medicalnote.ReadOnlyMedicalNoteList;
import seedu.medibook.model.patient.Ic;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonMediBookStorage mediBookStorage = new JsonMediBookStorage(getTempFilePath("mb"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonMedicalNoteListStorage medicalNoteListStorage =
                new JsonMedicalNoteListStorage(getTempFilePath("mnl"));
        JsonUserAccountsListStorage userAccountStorage = new JsonUserAccountsListStorage(getTempFilePath("account"));
        storageManager = new StorageManager(mediBookStorage, userPrefsStorage, medicalNoteListStorage,
                userAccountStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void getUserPrefsFilePath() {
        assertEquals(getTempFilePath("prefs"), storageManager.getUserPrefsFilePath());
    }

    @Test
    public void getMedicalNotesDirPath() {
        assertEquals(getTempFilePath("mnl"), storageManager.getMedicalNotesDirPath());
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6, false));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void mediBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonMediBookStorage} class.
         * More extensive testing of MediBook saving/reading is done in {@link JsonMediBookStorageTest} class.
         * Expected behaviour is that only patients' information are loaded and they are initialised with
         * empty medical note list.
         * Hence, the MediBook that we should check against after retrieving from storage is one where all patients
         * hold empty medical note list.
         */
        MediBook original = getTypicalMediBook();
        storageManager.saveMediBook(original);
        ReadOnlyMediBook retrieved = storageManager.readMediBook().get();
        MediBook originalWithAllEmptyMedicalNoteList = getTypicalMediBookWithAllEmptyMedicalNoteList();
        assertEquals(originalWithAllEmptyMedicalNoteList, new MediBook(retrieved));
    }

    @Test
    public void getMediBookFilePath() {
        assertNotNull(storageManager.getMediBookFilePath());
    }

    @Test
    public void medicalNotesReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonMedicalNoteListStorage} class.
         * More extensive testing of MedicalNoteList saving/reading is done
         * in {@link JsonMedicalNoteListStorageTest} class.
         */
        MedicalNoteList original = getTypicalMedicalNoteList();
        Ic ic = new Ic("T0012393D");
        storageManager.saveMedicalNoteList(original, ic);
        ReadOnlyMedicalNoteList retrieved = storageManager.readMedicalNoteList(ic).get();
        assertEquals(original, new MedicalNoteList(retrieved.getMedicalNoteList()));
    }

    @Test
    public void medicalNotesSaveRenameReadDelete() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonMedicalNoteListStorage} class.
         * More extensive testing of MedicalNoteList saving/renaming/reading/deleting is done
         * in {@link JsonMedicalNoteListStorageTest} class.
         */
        MedicalNoteList original = getTypicalMedicalNoteList();
        Ic oldIc = new Ic("T0012393D");
        storageManager.saveMedicalNoteList(original, oldIc);

        Ic newIc = new Ic("S4398349Z");
        storageManager.renameMedicalNoteList(oldIc, newIc);

        ReadOnlyMedicalNoteList retrieved = storageManager.readMedicalNoteList(newIc).get();
        assertEquals(original, new MedicalNoteList(retrieved.getMedicalNoteList()));

        storageManager.deleteMedicalNoteList(newIc);
        assertTrue(storageManager.readMedicalNoteList(newIc).isEmpty());
    }

    @Test
    public void medicalNotesSaveDeleteAllRead() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonMedicalNoteListStorage} class.
         * More extensive testing of MedicalNoteList saving/delete all/read is done
         * in {@link JsonMedicalNoteListStorageTest} class.
         */
        MedicalNoteList original = getTypicalMedicalNoteList();
        Ic ic1 = new Ic("T6837664L");
        storageManager.saveMedicalNoteList(original, ic1);

        Ic ic2 = new Ic("S9674263W");
        storageManager.saveMedicalNoteList(original, ic2);

        Ic ic3 = new Ic("S6848336I");
        storageManager.saveMedicalNoteList(original, ic3);

        ReadOnlyMedicalNoteList retrieved = storageManager.readMedicalNoteList(ic1).get();
        assertEquals(original, new MedicalNoteList(retrieved.getMedicalNoteList()));

        retrieved = storageManager.readMedicalNoteList(ic2).get();
        assertEquals(original, new MedicalNoteList(retrieved.getMedicalNoteList()));

        retrieved = storageManager.readMedicalNoteList(ic3).get();
        assertEquals(original, new MedicalNoteList(retrieved.getMedicalNoteList()));

        storageManager.deleteAllMedicalNoteList();

        assertTrue(storageManager.readMedicalNoteList(ic1).isEmpty());
        assertTrue(storageManager.readMedicalNoteList(ic2).isEmpty());
        assertTrue(storageManager.readMedicalNoteList(ic3).isEmpty());
    }

}
