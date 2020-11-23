package seedu.address.logic.commandshortcut;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commandshortcut.exceptions.CommandWordException;

class CommandShortcutTest {



    @Test
    void addShortcut() {
        CommandShortcut shortcut = new CommandShortcut(CommandWord.ADD_APPOINTMENT);
        assertDoesNotThrow(()-> shortcut.addShortcut("a_a"));
        assertTrue(shortcut.shortcutExist("a_a"));

        assertThrows(CommandWordException.class, ()->shortcut.addShortcut("a_a"));

        assertThrows(CommandWordException.class, ()-> shortcut.addShortcut("add_appt"));

        assertThrows(CommandWordException.class, ()-> shortcut.addShortcut("123456789012345678901"));

        assertThrows(CommandWordException.class, ()->shortcut.addShortcut("add  appt"));
    }

    @Test
    void shortcutExist() {
        CommandShortcut shortcut = new CommandShortcut(CommandWord.ADD_CLIENT);
        assertTrue(shortcut.shortcutExist("add_client"));
        assertFalse(shortcut.shortcutExist("a_c"));
        assertDoesNotThrow(()-> shortcut.addShortcut("a_c"));
        assertTrue(shortcut.shortcutExist("a_c"));
        assertFalse(shortcut.shortcutExist("a_C"));

    }

    @Test
    void removeShortcut() {
        CommandShortcut shortcut = new CommandShortcut(CommandWord.ADD_HAIRDRESSER);
        assertThrows(CommandWordException.class, ()->shortcut.removeShortcut("add_hairdresser"));
        assertThrows(CommandWordException.class, ()->shortcut.removeShortcut("a_h"));

        assertDoesNotThrow(()-> {
            shortcut.addShortcut("a_h");
            shortcut.removeShortcut("a_h");
        });


        assertFalse(shortcut.shortcutExist("a_h"));
    }

    @Test
    void getCommandWord() {
        CommandShortcut shortcut = new CommandShortcut(CommandWord.EDIT_APPOINTMENT);
        assertTrue(shortcut.getCommandWord() == CommandWord.EDIT_APPOINTMENT);
    }

    @Test
    void checkCommandWord() {
        CommandShortcut shortcut = new CommandShortcut(CommandWord.DELETE_APPOINTMENT);
        assertTrue(shortcut.checkCommandWord(CommandWord.DELETE_APPOINTMENT));
    }

    @Test
    void getAllShortcuts() {
        CommandShortcut shortcut = new CommandShortcut(CommandWord.FILTER_APPT);
        assertArrayEquals(shortcut.getAllShortcuts().toArray(), new String[] {});
        assertDoesNotThrow(()-> {
            shortcut.addShortcut("f_a");
            shortcut.addShortcut("f_b");
            shortcut.addShortcut("f_c");
            shortcut.addShortcut("f_d");
        });

        assertTrue(sameArrayList(shortcut.getAllShortcuts(),
                new ArrayList<String> (Arrays.asList("f_a", "f_b", "f_c", "f_d"))));
    }


    private boolean sameArrayList(ArrayList<String> a, ArrayList<String> b) {
        return a.containsAll(b) && b.containsAll(a);
    }
}
