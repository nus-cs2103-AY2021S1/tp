package seedu.resireg.model.alias;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.commands.AddCommand;
import seedu.resireg.logic.commands.ListStudentsCommand;

class CommandWordTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CommandWord(null));
    }

    @Test
    public void constructor_invalidFloor_throwsIllegalArgumentException() {
        String invalidCommandWord = "";
        assertThrows(IllegalArgumentException.class, () -> new CommandWord(invalidCommandWord));
    }

    @Test
    void isValidCommandWord() {
        // null command word
        assertThrows(NullPointerException.class, () -> CommandWord.isValidCommandWord(null));

        // invalid command word
        assertFalse(CommandWord.isValidCommandWord("")); // empty string
        assertFalse(CommandWord.isValidCommandWord(" ")); // spaces only
        assertFalse(CommandWord.isValidCommandWord("123")); // contains 3 digits
        assertFalse(CommandWord.isValidCommandWord("01")); // starts with 0
        assertFalse(CommandWord.isValidCommandWord("two words")); // contains multiple words


        // valid command word
        assertTrue(CommandWord.isValidCommandWord(AddCommand.COMMAND_WORD)); // 2 digits
        assertTrue(CommandWord.isValidCommandWord(ListStudentsCommand.COMMAND_WORD)); // 1 digit
        assertFalse(CommandWord.isValidCommandWord(AddCommand.COMMAND_WORD + " ")); // only non-numeric characters
        assertFalse(CommandWord.isValidCommandWord(ListStudentsCommand.COMMAND_WORD + "\t")); // contains non-numeric characters
    }

}
