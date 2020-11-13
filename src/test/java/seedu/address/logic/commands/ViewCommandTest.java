package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DAY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_DAY;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code ViewCommand}.
 */
public class ViewCommandTest {

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_DAY);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_DAY);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_DAY);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different day -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
