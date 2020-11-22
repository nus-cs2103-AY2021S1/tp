package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {
    @Test
    public void getNext_reachHistoryEnd_throwsNoSuchElementException() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.add("1");
        assertThrows(NoSuchElementException.class, () -> commandHistory.getNext());
    }

    @Test
    public void getPrevious_reachHistoryBeginning_throwsNoSuchElementException() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.add("1");

        commandHistory.getPrevious("");
        assertThrows(NoSuchElementException.class, () -> commandHistory.getPrevious(""));
    }

    @Test
    public void getPrevious_completeHistory_isMatching() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.add("1");
        commandHistory.add("2");
        commandHistory.add("3");
        assertAll(() -> assertEquals("3", commandHistory.getPrevious("")), () ->
                        assertEquals("2", commandHistory.getPrevious("")), () ->
                        assertEquals("1", commandHistory.getPrevious("")));
    }

}
