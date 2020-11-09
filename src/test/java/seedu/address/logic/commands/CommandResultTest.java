package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback", "category");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback", "category")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, "category")));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different", "category")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, "category")));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, "category")));

        //different category value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", "diff_category")));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback", "category");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback", "category").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different", "category").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false, "category").hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true, "category").hashCode());

        // different category value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", "diff_category").hashCode());
    }
}
