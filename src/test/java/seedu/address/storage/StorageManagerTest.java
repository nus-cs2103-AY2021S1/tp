package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientRecords;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomList;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.PatientRecords;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.model.room.Room;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonPatientRecordsStorage covigentAppStorage = new JsonPatientRecordsStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonRoomOccupancyStorage roomOccupancyStorage = new JsonRoomOccupancyStorage(getTempFilePath("ro"));
        //files ro is short form for roomsOccupied.
        storageManager = new StorageManager(covigentAppStorage, roomOccupancyStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void covigentAppReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonPatientRecordsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonPatientRecordsStorageTest} class.
         */
        PatientRecords original = getTypicalPatientRecords();
        storageManager.savePatientRecords(original);
        ReadOnlyList<Patient> retrieved = storageManager.readPatientRecords().get();
        assertEquals(original, new PatientRecords(retrieved));
    }

    @Test
    public void getCovigentAppFilePath() {
        assertNotNull(storageManager.getPatientRecordsFilePath());
    }

    @Test
    public void saveRoomOccupancyStorage() throws IOException, DataConversionException {
        RoomList original = getTypicalRoomList();
        storageManager.saveRoomList(original);
        ReadOnlyList<Room> readOnlyRoomList = storageManager.readRoomOccupancyStorage().get();
        assertEquals(original, readOnlyRoomList);
    }
}
