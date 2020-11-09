package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalVendors.ALICE;
import static seedu.address.testutil.TypicalVendors.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.menu.MenuManager;
import seedu.address.model.order.OrderManager;
import seedu.address.model.vendor.VendorManager;
import seedu.address.testutil.VendorManagerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new VendorManager(), new VendorManager(modelManager.getVendorManager()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setVendorManagerFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setVendorManagerFilePath(Paths.get("new/address/book/file/path"));
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
    public void setVendorManagerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setVendorManagerFilePath(null));
    }

    @Test
    public void setVendorManagerFilePath_validPath_setsVendorManagerFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setVendorManagerFilePath(path);
        assertEquals(path, modelManager.getVendorManagerFilePath());
    }

    @Test
    public void hasVendor_nullVendor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasVendor(null));
    }

    @Test
    public void hasVendor_vendorNotInVendorManager_returnsFalse() {
        assertFalse(modelManager.hasVendor(ALICE));
    }

    //@Test
    //public void hasVendor_vendorInVendorManager_returnsTrue() {
    //        modelManager.addVendor(ALICE);
    //        assertTrue(modelManager.hasVendor(ALICE));
    //}

    @Test
    public void getFilteredVendorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getObservableVendorList().remove(0));
    }

    @Test
    public void equals() {
        VendorManager vendorManager = new VendorManagerBuilder().withVendor(ALICE).withVendor(BENSON).build();
        VendorManager differentVendorManager = new VendorManager();
        UserPrefs userPrefs = new UserPrefs();
        List<MenuManager> menus = new ArrayList<>();
        menus.add(new MenuManager(ALICE.getMenu()));
        menus.add(new MenuManager(BENSON.getMenu()));

        // same values -> returns true
        modelManager = new ModelManager(vendorManager, userPrefs, menus, new OrderManager());
        ModelManager modelManagerCopy = new ModelManager(vendorManager, userPrefs, menus, new OrderManager());
        modelManager.selectVendor(0);
        modelManagerCopy.selectVendor(0);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different vendorManager -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentVendorManager, userPrefs,
                new ArrayList<>(), new OrderManager())));

        // different filteredList -> returns false
        //        String[] keywords = ALICE.getName().fullName.split("\\s+");
        //        modelManager.updateFilteredFoodList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        //        assertFalse(modelManager.equals(new ModelManager(vendorManager, userPrefs)));

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setVendorManagerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(vendorManager, differentUserPrefs,
                menus, new OrderManager())));
    }
}
