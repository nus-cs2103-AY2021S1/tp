package seedu.address.logic.commandshortcut;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CommandWordTest {

    @Test
    void getDefaultWord() {
        CommandWord word = CommandWord.ADD_APPOINTMENT;
        assertTrue(word.getDefaultWord().equals("add_appt"));
    }

    @Test
    void testToString() {
        CommandWord word = CommandWord.ADD_CLIENT;
        assertTrue(word.toString().equals("add_client"));
    }

    @Test
    void getCommandWord() {
        CommandWord word = CommandWord.getCommandWord("edit_hairdresser");
        assertTrue(word == CommandWord.EDIT_HAIRDRESSER);

    }
}
