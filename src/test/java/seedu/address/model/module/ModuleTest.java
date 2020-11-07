package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS50;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_CS50;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS1010S;
import static seedu.address.testutil.TypicalModules.CS1101S;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalPersons.IDA;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.builders.ModuleBuilder;

public class ModuleTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Module module = new ModuleBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> module.getInstructors().remove(0));
    }

    @Test
    public void isSameModule() {
        // same object -> returns true
        assertTrue(CS1010S.isSameModule(CS1010S));

        // null -> returns false
        assertFalse(CS1010S.isSameModule(null));

        // different code, same name, different attributes -> returns false
        assertFalse(CS1010S.isSameModule(CS1101S));

        // different code, same name, same attributes -> returns false
        Person[] instructorsCs1101s = new Person[CS1101S.getInstructors().size()];
        CS1101S.getInstructors().toArray(instructorsCs1101s);

        Module cs50 = new ModuleBuilder(CS1101S).withCode("CS50").build();
        assertFalse(CS1101S.isSameModule(cs50));

        // same code, same name, different attributes -> returns true
        Module editedCs1010S = new ModuleBuilder(CS1010S).withInstructors(IDA).build();
        assertTrue(CS1010S.isSameModule(editedCs1010S));

        // same code, different name, same attributes -> returns true
        editedCs1010S = new ModuleBuilder(CS1010S).withName(VALID_MODULE_NAME_CS50).build();
        assertTrue(CS1010S.isSameModule(editedCs1010S));

        // same code, different name, different attributes -> returns true
        editedCs1010S = new ModuleBuilder(CS1010S)
                .withName(VALID_MODULE_NAME_CS50)
                .withInstructors(instructorsCs1101s).build();
        assertTrue(CS1010S.isSameModule(editedCs1010S));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Module copyCs1010s = new ModuleBuilder(CS1010S).build();
        assertEquals(copyCs1010s, CS1010S);

        // same object -> returns true
        assertEquals(CS1010S, CS1010S);

        // null -> returns false
        assertNotEquals(CS1010S, null);

        // different type -> returns false
        assertNotEquals(CS1010S, 5);

        // different Module -> returns false
        assertNotEquals(CS2030, CS1010S);

        // different code -> returns false
        Module editedCs1010s = new ModuleBuilder(CS1010S).withCode(VALID_MODULE_CODE_CS50).build();
        assertNotEquals(editedCs1010s, CS1010S);

        // different name -> returns false
        editedCs1010s = new ModuleBuilder(CS1010S).withName(VALID_MODULE_NAME_CS50).build();
        assertNotEquals(editedCs1010s, CS1010S);

        // different instructors -> returns false
        Person[] instructorsCs1101s = new Person[CS1101S.getInstructors().size()];
        CS1101S.getInstructors().toArray(instructorsCs1101s);

        editedCs1010s = new ModuleBuilder(CS1010S).withInstructors(instructorsCs1101s).build();
        assertNotEquals(editedCs1010s, CS1010S);
    }
}
