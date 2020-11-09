package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_NAME_A;
import static seedu.address.testutil.TypicalModules.COM_ORG;
import static seedu.address.testutil.TypicalModules.MOD_A;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

public class ModuleTest {

    //@Test
    //public void asObservableList_modifyList_throwsUnsupportedOperationException() {
    //Module module = new ModuleBuilder().build();
    //assertThrows(UnsupportedOperationException.class, () -> module.getTags().remove(0));}

    @Test
    public void isSameModule() {
        // same object -> returns true
        assertTrue(COM_ORG.isSameModule(COM_ORG));

        // null -> returns false
        assertFalse(COM_ORG.isSameModule(null));

        Module updatedModule;
        // different module name -> returns false
        updatedModule = new ModuleBuilder(COM_ORG).withName(VALID_MOD_NAME_A).build();
        assertFalse(COM_ORG.isSameModule(updatedModule));

        // same module name, different attributes (Grade) -> returns true
        updatedModule = new ModuleBuilder(COM_ORG).withGrade("A+")
               .build();
        assertTrue(COM_ORG.isSameModule(updatedModule));

        // same module name, same grade, different attributes (number of MCs) -> returns true
        updatedModule = new ModuleBuilder(COM_ORG).withGrade("A-").withModularCredit(2).build();
        assertTrue(COM_ORG.isSameModule(updatedModule));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Module moduleCopy = new ModuleBuilder(COM_ORG).build();
        assertTrue(COM_ORG.equals(moduleCopy));

        // same object -> returns true
        assertTrue(COM_ORG.equals(COM_ORG));

        // null -> returns false
        assertFalse(COM_ORG.equals(null));

        // different type -> returns false
        assertFalse(COM_ORG.equals(5));

        // different module -> returns false
        assertFalse(COM_ORG.equals(MOD_A));

        // different module name -> returns false
        Module updatedModule = new ModuleBuilder(COM_ORG).withName(VALID_MOD_NAME_A).build();
        assertFalse(COM_ORG.equals(updatedModule));

        // different grade -> returns false
        updatedModule = new ModuleBuilder(COM_ORG).withGrade("A+").build();
        assertFalse(COM_ORG.equals(updatedModule));
    }
}
