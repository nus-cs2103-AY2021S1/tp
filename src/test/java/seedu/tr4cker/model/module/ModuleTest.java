package seedu.tr4cker.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ModuleTest {

    private Module exampleModule =
            new Module("NAME1", new ModuleCode("CODE1"));
    private Module exampleModuleCopy =
            new Module("NAME1", new ModuleCode("CODE1"));
    private Module moduleWithDiffCode =
            new Module("NAME1", new ModuleCode("CODE2"));
    private Module moduleWithDiffName =
            new Module("NAME2", new ModuleCode("CODE1"));
    private Module moduleWithLowerCaseCode =
            new Module("NAME1", new ModuleCode("code1"));

    @Test
    void isValidModuleName() {
        // null name
        assertThrows(NullPointerException.class, () -> Module.isValidModuleName(null));

        // invalid name
        assertFalse(Module.isValidModuleName("@@@")); // non-alphanumeric
        assertFalse(Module.isValidModuleName("      ")); // spaces
        assertFalse(Module.isValidModuleName("")); // empty string

        // valid name
        assertTrue(Module.isValidModuleName("graded assignment")); // alphabets only
        assertTrue(Module.isValidModuleName("12345")); // numbers only
        assertTrue(Module.isValidModuleName("assignment 1")); // alphanumeric characters
        assertTrue(Module.isValidModuleName("Homework Assignment")); // with capital letters
        assertTrue(Module.isValidModuleName("A Very Long name for my Homework")); // long names
    }

    @Test
    void isSameModule() {
        // same object -> returns true
        assertTrue(exampleModule.isSameModule(exampleModule));

        // null -> returns false
        assertFalse(exampleModule.isSameModule(null));

        // different module code -> returns false
        assertFalse(exampleModule.isSameModule(moduleWithDiffCode));

        // same code different name -> returns true
        assertTrue(exampleModule.isSameModule(moduleWithDiffName));

        // case insensitive module code
        assertTrue(exampleModule.isSameModule(moduleWithLowerCaseCode));
    }

    @Test
    void testEquals() {
        // same values -> returns true
        assertTrue(exampleModule.equals(exampleModuleCopy));

        // same object -> returns true
        assertTrue(exampleModule.equals(exampleModule));

        // null -> returns false
        assertFalse(exampleModule.equals(null));

        // different type -> returns false
        assertFalse(exampleModule.equals(5));

        // different name -> returns false
        assertFalse(exampleModule.equals(moduleWithDiffName));

        // different code -> returns false
        assertFalse(exampleModule.equals(moduleWithDiffCode));
    }

    @Test
    void testHashCode() {
        String moduleName = "name";
        ModuleCode moduleCode = new ModuleCode("code");
        Module module1 = new Module(moduleName, moduleCode);
        Module module2 = new Module(moduleName, moduleCode);
        assertEquals(module1.hashCode(), module2.hashCode());
    }
}
