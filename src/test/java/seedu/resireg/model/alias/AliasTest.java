package seedu.resireg.model.alias;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.commands.AddCommand;
import seedu.resireg.logic.commands.ListStudentsCommand;

class AliasTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Alias(null));
    }

    @Test
    public void constructor_invalidFloor_throwsIllegalArgumentException() {
        String invalidFloor = "";
        assertThrows(IllegalArgumentException.class, () -> new Alias(invalidFloor));
    }

    @Test
    void isValidAlias() {
        // null alias
        assertThrows(NullPointerException.class, () -> Alias.isValidAlias(null));

        // invalid alias
        assertFalse(Alias.isValidAlias("")); // empty string
        assertFalse(Alias.isValidAlias(" ")); // spaces only
        assertFalse(Alias.isValidAlias("123")); // contains 3 digits
        assertFalse(Alias.isValidAlias("01")); // starts with 0
        assertFalse(Alias.isValidAlias(AddCommand.COMMAND_WORD)); // only non-numeric characters
        assertFalse(Alias.isValidAlias(ListStudentsCommand.COMMAND_WORD)); // contains non-numeric characters
        assertFalse(Alias.isValidAlias("two words")); // contains multiple words


        // valid alias
        assertTrue(Alias.isValidAlias("ro")); // 2 digits
        assertTrue(Alias.isValidAlias("stu")); // 1 digit

    }

}
