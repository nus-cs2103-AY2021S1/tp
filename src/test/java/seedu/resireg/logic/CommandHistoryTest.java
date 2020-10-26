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

    @BeforeEach
    public void setUp() {
        history = new CommandHistory();
    }

    @Test
    public void constructor_withCommandHistory_copiesCommandHistory() {
        final CommandHistory historyWithC = new CommandHistory();
        historyWithC.add("c");

        assertEquals(historyWithC, new CommandHistory(historyWithC));
    }

    @Test
    public void add() {
        final String validCommand = "rooms";
        final String invalidCommand = "something";

        history.add(validCommand);
        history.add(invalidCommand);
        assertEquals(Arrays.asList(validCommand, invalidCommand), history.getHistory());
    }

    @Test
    void getHistory() {
        final CommandHistory historyWithCD = new CommandHistory();
        historyWithCD.add("c");
        historyWithCD.add("d");

        assertEquals(Arrays.asList("c", "d"), historyWithCD.getHistory());
    }

    @Test
    void equals() {
        final CommandHistory historyWithC = new CommandHistory();
        historyWithC.add("c");
        final CommandHistory anotherHistoryWithC = new CommandHistory();
        anotherHistoryWithC.add("c");
        final CommandHistory historyWithD = new CommandHistory();
        historyWithD.add("d");

        // same object -> returns true
        assertTrue(historyWithC.equals(historyWithC));

        // same values -> returns true
        assertTrue(historyWithC.equals(anotherHistoryWithC));

        // null -> returns false
        assertFalse(historyWithD.equals(null));

        // different types -> returns false
        assertFalse(historyWithD.equals(5.0f));

        // different values -> returns false
        assertFalse(historyWithD.equals(historyWithC));
    }

    @Test
    void hashcode() {
        final CommandHistory historyWithC = new CommandHistory();
        historyWithC.add("c");
        final CommandHistory anotherHistoryWithC = new CommandHistory();
        anotherHistoryWithC.add("c");
        final CommandHistory historyWithD = new CommandHistory();
        historyWithD.add("d");

        // same values -> returns same hashcode
        assertEquals(historyWithC.hashCode(), anotherHistoryWithC.hashCode());

        // different values -> returns different hashcode
        assertNotEquals(historyWithC.hashCode(), historyWithD.hashCode());
    }
}
