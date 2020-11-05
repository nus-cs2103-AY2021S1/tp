package seedu.cc.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = CommandResultFactory.createDefaultCommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(CommandResultFactory.createDefaultCommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult(
            "feedback", false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(CommandResultFactory.createDefaultCommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(CommandResultFactory.createCommandResultForHelpCommand("different")));

        // different exit value -> returns false
        assertFalse(commandResult.equals(CommandResultFactory.createCommandResultForExitCommand("different")));

        // different list changing value -> returns false
        assertFalse(commandResult.equals(CommandResultFactory
            .createCommandResultForEntryListChangingCommand("different")));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = CommandResultFactory.createDefaultCommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), CommandResultFactory.createDefaultCommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
            CommandResultFactory.createDefaultCommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
            CommandResultFactory.createCommandResultForHelpCommand("different").hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
            CommandResultFactory.createCommandResultForExitCommand("different").hashCode());

        // different list changing value -> return different hashcode
        assertNotEquals(commandResult.hashCode(),
            CommandResultFactory.createCommandResultForEntryListChangingCommand("different").hashCode());
    }
}
