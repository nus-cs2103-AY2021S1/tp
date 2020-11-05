package seedu.resireg.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandHistoryTest {
    private CommandHistory history;
    private final CommandHistory historyWithC = new CommandHistory();
    private final CommandHistory historyWithD = new CommandHistory();
    private final CommandHistory historyWithCD = new CommandHistory();

    @BeforeEach
    public void setUp() {
        history = new CommandHistory();
        historyWithC.add("c");
        historyWithD.add("d");
        historyWithCD.add("c");
        historyWithCD.add("d");
    }

    @Test
    public void constructor_withCommandHistory_copiesCommandHistory() {
        assertEquals(historyWithC, new CommandHistory(historyWithC));
    }

    @Test
    public void add() {
        String validCommand = "rooms"; // one word command
        String validCommandTwo = "edit-room 1 t/CN"; // multi word command
        String invalidCommand = "something";
        String invalidCommandTwo = ""; // check empty string not added

        history.add(validCommand);
        history.add(invalidCommand);
        history.add(validCommandTwo);
        history.add(invalidCommandTwo);

        assertEquals(Arrays.asList(validCommand, invalidCommand, validCommandTwo),
                history.getHistory());
    }

    @Test
    void getHistory() {
        assertEquals(Arrays.asList("c", "d"), historyWithCD.getHistory());
    }

    @Test
    void getCounter() {
        assertEquals(2, historyWithCD.getCounter());
    }

    @Test
    void getCommandResultString() {
        // empty list -> empty string
        assertEquals("", history.getCommandResultString());

        // singleton list -> string as described
        String expected = "1\tc";
        assertEquals(expected, historyWithC.getCommandResultString());

        // multiple element list -> string as described
        String expectedTwo = "1\tc\n2\td";
        assertEquals(expectedTwo, historyWithCD.getCommandResultString());
    }

    @Test
    void equals() {
        final CommandHistory anotherHistoryWithC = new CommandHistory(historyWithC);
        final CommandHistory anotherHistoryWithD = new CommandHistory(historyWithD);

        anotherHistoryWithD.setCounter(3);

        // same object -> returns true
        assertTrue(historyWithC.equals(historyWithC));

        // same values, same counter -> returns true
        assertTrue(historyWithC.equals(anotherHistoryWithC));

        // null -> returns false
        assertFalse(historyWithD.equals(null));

        // different types -> returns false
        assertFalse(historyWithD.equals(5.0f));

        // different values, same counter -> returns false
        assertFalse(historyWithD.equals(historyWithC));

        // same values, different counter -> returns false
        assertFalse(historyWithD.equals(anotherHistoryWithD));
    }

    @Test
    void hashcode() {
        final CommandHistory anotherHistoryWithC = new CommandHistory(historyWithC);

        // same values -> returns same hashcode
        assertEquals(historyWithC.hashCode(), anotherHistoryWithC.hashCode());

        // different values -> returns different hashcode
        assertNotEquals(historyWithC.hashCode(), historyWithD.hashCode());
    }
}
