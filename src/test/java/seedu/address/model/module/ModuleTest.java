package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_CS1000;
import static seedu.address.testutil.TypicalModules.CS2100;
import static seedu.address.testutil.TypicalModules.CS2101;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.ModuleBuilder;


public class ModuleTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Module module = new ModuleBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> module.getClassmates().remove(0));
    }

    @Test
    public void isSameModule() {
        // same object -> returns true
        assertTrue(CS2100.isSameModule(CS2100));

        // null -> returns false
        assertFalse(CS2100.isSameModule(null));

        // different name -> returns false
        Module editedCS2100Module = new ModuleBuilder(CS2100).withName(VALID_MODULE_NAME_CS1000).build();
        assertFalse(CS2100.isSameModule(editedCS2100Module));

        // same name different classmates -> returns false
        Module CS2100Module = new ModuleBuilder(CS2100).build();
        Set<Person> editedClassmates = CS2101.getClassmates();
        editedCS2100Module = new ModuleBuilder(CS2100).withMembers(editedClassmates).build();
        assertFalse(CS2100Module.isSameModule(editedCS2100Module));

        // same name same classmates -> return true
        editedCS2100Module = new ModuleBuilder().withMembers(CS2100.getClassmates())
            .withName(CS2100.getModuleName().getModuleName()).build();
        assertTrue(CS2100Module.isSameModule(editedCS2100Module));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Module cs2100ModuleCopy = new ModuleBuilder(CS2100).build();
        assertTrue(CS2100.equals(cs2100ModuleCopy));

        // same object -> returns true
        assertTrue(CS2100.equals(CS2100));

        // null -> returns false
        assertFalse(CS2100.equals(null));

        // different type -> returns false
        assertFalse(CS2100.equals(5));

        // different meeting -> returns false
        assertFalse(CS2100.equals(CS2101));

        // different name -> returns false
        Module editedCS2100Module = new ModuleBuilder(CS2100).withName(VALID_MODULE_NAME_CS1000).build();
        assertFalse(CS2100.equals(editedCS2100Module));


        // different participants -> returns true
        editedCS2100Module = new ModuleBuilder(CS2100).withMembers(CS2101.getClassmates()).build();
        assertTrue(CS2100.equals(editedCS2100Module));
    }
}
