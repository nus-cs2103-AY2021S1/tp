package seedu.address.model.history;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {

    @Test
    void clearHistory() {
        CommandHistory.clearHistory();
        assertEquals("Here are your command history:", CommandHistory.getCommandHistory());
    }
}
