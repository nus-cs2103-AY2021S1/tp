package seedu.homerce.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.homerce.testutil.client.TypicalClients.getTypicalClientManager;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.homerce.commons.core.GuiSettings;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ReadOnlyClientManager;
import seedu.homerce.storage.appointment.AppointmentStorage;
import seedu.homerce.storage.appointment.JsonAppointmentStorage;
import seedu.homerce.storage.client.JsonClientStorage;
import seedu.homerce.storage.expense.ExpenseStorage;
import seedu.homerce.storage.expense.JsonExpenseStorage;
import seedu.homerce.storage.revenue.JsonRevenueStorage;
import seedu.homerce.storage.revenue.RevenueStorage;
import seedu.homerce.storage.service.JsonServiceStorage;
import seedu.homerce.storage.service.ServiceStorage;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonClientStorage clientManagerStorage = new JsonClientStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        ServiceStorage serviceStorage = new JsonServiceStorage(getTempFilePath("services"));
        RevenueStorage revenueStorage = new JsonRevenueStorage(getTempFilePath("revenues"));
        ExpenseStorage expenseStorage = new JsonExpenseStorage(getTempFilePath("expenses"));
        AppointmentStorage appointmentStorage = new JsonAppointmentStorage(getTempFilePath("appointments"));

        storageManager = new StorageManager(userPrefsStorage, clientManagerStorage, serviceStorage,
                revenueStorage, expenseStorage, appointmentStorage);
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
    public void clientManagerReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonClientStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonClientStorageTest} class.
         */
        ClientManager original = getTypicalClientManager();
        storageManager.saveClientManager(original);
        ReadOnlyClientManager retrieved = storageManager.readClientManager().get();
        assertEquals(original, new ClientManager(retrieved));
    }

    @Test
    public void getClientManagerFilePath() {
        assertNotNull(storageManager.getClientManagerFilePath());
    }

}
