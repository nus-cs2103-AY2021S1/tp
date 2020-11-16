package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {

    private static final String COMMAND_1 = "Testing 1";
    private static final String COMMAND_2 = "Testing 2";

    private CommandHistory commandHistory;

    @Test
    public void commandHistorySingleton() {
        // Initialisation of singleton
        commandHistory = CommandHistory.getInstance();
        assertNotNull(commandHistory);

        // Fetch previous command - non-existent
        assertEquals(Optional.empty(), commandHistory.fetchPrevious());

        // Fetch next command - non-existent
        assertEquals(Optional.empty(), commandHistory.fetchNext());

        // Add new command to history - null
        assertThrows(NullPointerException.class, () -> commandHistory.push(null));

        // Add new valid command to history
        commandHistory.push(COMMAND_1);

        // Fetch previous command a few times - should get the only command in history
        assertEquals(COMMAND_1, commandHistory.fetchPrevious().get());
        assertEquals(COMMAND_1, commandHistory.fetchPrevious().get());
        assertEquals(COMMAND_1, commandHistory.fetchPrevious().get());

        // Fetch next command a few times - should get the only command in history
        assertEquals(COMMAND_1, commandHistory.fetchNext().get());
        assertEquals(COMMAND_1, commandHistory.fetchNext().get());
        assertEquals(COMMAND_1, commandHistory.fetchNext().get());

        // Add another new valid command to history
        commandHistory.push(COMMAND_2);

        // Fetch previous commands - commands should match and should "roll over"
        assertEquals(COMMAND_2, commandHistory.fetchPrevious().get());
        assertEquals(COMMAND_1, commandHistory.fetchPrevious().get());
        assertEquals(COMMAND_2, commandHistory.fetchPrevious().get());

        // Fetch next commands - commands should match and should "roll over"
        assertEquals(COMMAND_1, commandHistory.fetchNext().get());
        assertEquals(COMMAND_2, commandHistory.fetchNext().get());
        assertEquals(COMMAND_1, commandHistory.fetchNext().get());

    }
}
