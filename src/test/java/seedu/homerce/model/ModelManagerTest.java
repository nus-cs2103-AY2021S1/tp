package seedu.homerce.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.client.TypicalClients.ALICE;
import static seedu.homerce.testutil.client.TypicalClients.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.GuiSettings;
import seedu.homerce.model.client.predicate.ClientNamePredicate;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;
import seedu.homerce.testutil.client.ClientManagerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ClientManager(), new ClientManager(modelManager.getClientManager()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setClientManagerFilePath(Paths.get("homerce/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setClientManagerFilePath(Paths.get("new/homerce/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setClientManagerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setClientManagerFilePath(null));
    }

    @Test
    public void setClientManagerFilePath_validPath_setsClientManagerFilePath() {
        Path path = Paths.get("homerce/book/file/path");
        modelManager.setClientManagerFilePath(path);
        assertEquals(path, modelManager.getClientManagerFilePath());
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInClientManager_returnsFalse() {
        assertFalse(modelManager.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInClientManager_returnsTrue() {
        modelManager.addClient(ALICE);
        assertTrue(modelManager.hasClient(ALICE));
    }

    @Test
    public void getFilteredClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredClientList().remove(0));
    }

    @Test
    public void equals() {
        ClientManager clientManager = new ClientManagerBuilder().withClient(ALICE).withClient(BENSON).build();
        ClientManager differentClientManager = new ClientManager();
        UserPrefs userPrefs = new UserPrefs();
        ServiceManager serviceManager = new ServiceManager();
        RevenueTracker revenueTracker = new RevenueTracker();
        ExpenseTracker expenseTracker = new ExpenseTracker();
        AppointmentManager appointmentManager = new AppointmentManager();

        // same values -> returns true
        modelManager = new ModelManager(userPrefs, clientManager, serviceManager,
                revenueTracker, expenseTracker, appointmentManager);
        ModelManager modelManagerCopy = new ModelManager(userPrefs, clientManager, serviceManager, revenueTracker,
            expenseTracker, appointmentManager);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different clientManager -> returns false
        assertFalse(modelManager
            .equals(new ModelManager(userPrefs, differentClientManager,
                    serviceManager, revenueTracker, expenseTracker, appointmentManager)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredClientList(new ClientNamePredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(userPrefs, clientManager,
                serviceManager, revenueTracker, expenseTracker, appointmentManager)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setClientManagerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(differentUserPrefs, clientManager, serviceManager,
                revenueTracker, expenseTracker, appointmentManager)));
    }
}
