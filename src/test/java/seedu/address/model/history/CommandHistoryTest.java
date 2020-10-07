package seedu.address.model.history;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {

    @Test
    void clearHistory() {
        CommandHistory.clearHistory();
        assertEquals("Here are your command history:", CommandHistory.getCommandHistory());
    }

    @Test
    void peekNext() {
        CommandHistory.clearHistory();
        CommandHistory.addUsedCommand("list");
        CommandHistory.addUsedCommand("history");
        CommandHistory.addUsedCommand("clear");
        assertEquals("clear", CommandHistory.peekNext());
        assertEquals("history", CommandHistory.peekNext());
        assertEquals("list", CommandHistory.peekNext());
    }

    @Test
    void peekPrev() {
        CommandHistory.clearHistory();
        CommandHistory.addUsedCommand("list");
        CommandHistory.addUsedCommand("history");
        CommandHistory.addUsedCommand("clear");
        assertEquals("clear", CommandHistory.peekNext());
        assertEquals("history", CommandHistory.peekNext());
        assertEquals("list", CommandHistory.peekNext());
        assertEquals("history", CommandHistory.peekPrev());
        assertEquals("clear", CommandHistory.peekPrev());
        assertEquals("", CommandHistory.peekPrev());
    }
}
