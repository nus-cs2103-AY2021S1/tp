package seedu.clinic.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinic.model.Model.PREDICATE_SHOW_ALL_SUPPLIERS;
import static seedu.clinic.model.Model.PREDICATE_SHOW_ALL_WAREHOUSES;
import static seedu.clinic.testutil.Assert.assertThrows;
import static seedu.clinic.testutil.TypicalMacro.MACRO_AS;
import static seedu.clinic.testutil.TypicalSupplier.ALICE;
import static seedu.clinic.testutil.TypicalSupplier.BENSON;
import static seedu.clinic.testutil.TypicalWarehouse.A;
import static seedu.clinic.testutil.TypicalWarehouse.B;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.clinic.commons.core.GuiSettings;
import seedu.clinic.model.attribute.NameContainsKeywordsPredicateForSupplier;
import seedu.clinic.model.attribute.NameContainsKeywordsPredicateForWarehouse;
import seedu.clinic.testutil.ClinicBuilder;
import seedu.clinic.testutil.UserMacrosBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Clinic(), new Clinic(modelManager.getClinic()));
        assertEquals(new UserMacros(), new UserMacros(modelManager.getUserMacros()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setClinicFilePath(Paths.get("clinic/book/file/path"));
        userPrefs.setUserMacrosFilePath(Paths.get("userMacros/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setClinicFilePath(Paths.get("new/clinic/book/file/path"));
        userPrefs.setUserMacrosFilePath(Paths.get("new/userMacros/book/file/path"));
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
    public void setClinicFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setClinicFilePath(null));
    }

    @Test
    public void setUserMacrosFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserMacrosFilePath(null));
    }

    @Test
    public void setClinicFilePath_validPath_setsClinicFilePath() {
        Path path = Paths.get("clinic/book/file/path");
        modelManager.setClinicFilePath(path);
        assertEquals(path, modelManager.getClinicFilePath());
    }

    @Test
    public void setUserMacrosFilePath_validPath_setsUserMacrosFilePath() {
        Path path = Paths.get("userMacros/book/file/path");
        modelManager.setUserMacrosFilePath(path);
        assertEquals(path, modelManager.getUserMacrosFilePath());
    }

    @Test
    public void hasSupplier_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasSupplier(null));
    }

    @Test
    public void hasWarehouse_nullWarehouse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasWarehouse(null));
    }

    @Test
    public void hasSupplierByName_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasSupplier(null));
    }

    @Test
    public void hasMacro_nullMacro_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasMacro(null));
    }

    @Test
    public void hasSupplier_supplierNotInClinic_returnsFalse() {
        assertFalse(modelManager.hasSupplier(ALICE));
    }

    @Test
    public void hasWarehouse_warehouseNotInClinic_returnsFalse() {
        assertFalse(modelManager.hasWarehouse(A));
    }

    @Test
    public void hasSupplierByName_supplierNotInClinic_returnsFalse() {
        assertFalse(modelManager.hasSupplier(ALICE));
    }

    @Test
    public void hasMacro_macroNotInUserMacros_returnsFalse() {
        assertFalse(modelManager.hasMacro(MACRO_AS));
    }

    @Test
    public void hasSupplier_supplierInClinic_returnsTrue() {
        modelManager.addSupplier(ALICE);
        assertTrue(modelManager.hasSupplier(ALICE));
    }

    @Test
    public void hasWarehouse_warehouseInClinic_returnsTrue() {
        modelManager.addWarehouse(A);
        assertTrue(modelManager.hasWarehouse(A));
    }

    @Test
    public void hasSupplierByName_supplierInClinic_returnsTrue() {
        modelManager.addSupplier(ALICE);
        assertTrue(modelManager.hasSupplier(ALICE));
    }

    @Test
    public void hasMacro_macroInUserMacros_returnsTrue() {
        modelManager.addMacro(MACRO_AS);
        assertTrue(modelManager.hasMacro(MACRO_AS));
    }

    @Test
    public void getMacro_macroInUserMacros_returnMacroInOptional() {
        modelManager.addMacro(MACRO_AS);
        assertEquals(modelManager.getMacro(MACRO_AS.getAlias()).orElseThrow(), MACRO_AS);
        assertEquals(modelManager.getMacro(MACRO_AS.getAlias().aliasString).orElseThrow(), MACRO_AS);
    }

    @Test
    public void getMacro_macroNotInUserMacros_returnEmptyOptional() {
        assertTrue(modelManager.getMacro(MACRO_AS.getAlias()).isEmpty());
        assertTrue(modelManager.getMacro(MACRO_AS.getAlias().aliasString).isEmpty());
    }

    @Test
    public void getFilteredSupplierList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredSupplierList().remove(0));
    }

    @Test
    public void getFilteredWarehouseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> modelManager.getFilteredWarehouseList().remove(0));
    }

    @Test
    public void getMacroList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> modelManager.getMacroList().remove(0));
    }

    @Test
    public void equals() {
        Clinic clinic = new ClinicBuilder()
                .withSupplier(ALICE)
                .withSupplier(BENSON)
                .withWarehouse(A)
                .withWarehouse(B)
                .build();

        UserMacros userMacros = new UserMacrosBuilder().withMacro(MACRO_AS).build();
        Clinic differentClinic = new Clinic();
        UserPrefs userPrefs = new UserPrefs();
        UserMacros differentUserMacros = new UserMacros();
        CommandHistory commandHistory = new CommandHistory();

        // same values -> returns true
        modelManager = new ModelManager(clinic, userPrefs, userMacros, commandHistory);
        ModelManager modelManagerCopy = new ModelManager(clinic, userPrefs, userMacros, commandHistory);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different clinic -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentClinic, userPrefs, userMacros, commandHistory)));

        // different userMacros -> returns false
        assertFalse(modelManager.equals(new ModelManager(clinic, userPrefs, differentUserMacros, commandHistory)));

        // different filteredSupplierList -> returns false
        modelManager.updateFilteredSupplierList(new NameContainsKeywordsPredicateForSupplier(
                ALICE.getName().fullName));
        assertFalse(modelManager.equals(new ModelManager(clinic, userPrefs, userMacros, commandHistory)));

        // different filteredWarehouseList -> returns false
        modelManager.updateFilteredWarehouseList(new NameContainsKeywordsPredicateForWarehouse(
                A.getName().fullName));
        assertFalse(modelManager.equals(new ModelManager(clinic, userPrefs, userMacros, commandHistory)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredSupplierList(PREDICATE_SHOW_ALL_SUPPLIERS);
        modelManager.updateFilteredWarehouseList(PREDICATE_SHOW_ALL_WAREHOUSES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setClinicFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(clinic, differentUserPrefs, userMacros, commandHistory)));
    }
}
