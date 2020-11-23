package seedu.tr4cker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");
        LocalDate localDate = LocalDate.now();
        YearMonth yearMonth = YearMonth.now();
        CommandResult commandResult1 = new CommandResult("feedback", localDate, yearMonth);

        // same values -> returns true
        assertEquals(new CommandResult("feedback"), commandResult);
        assertEquals(new CommandResult("feedback", false, false), commandResult);
        assertEquals(new CommandResult("feedback", localDate, yearMonth), commandResult1);

        // same object -> returns true
        assertEquals(commandResult, commandResult);

        // null -> returns false
        assertNotEquals(commandResult, null);

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertNotEquals(new CommandResult("different"), commandResult);

        // different showHelp value -> returns false
        assertNotEquals(CommandResult.createHelpTabSwitchCommandResult("feedback"), commandResult);

        // different exit value -> returns false
        assertNotEquals(new CommandResult("feedback", false, true), commandResult);
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), CommandResult.createHelpTabSwitchCommandResult("feedback")
                .hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());
    }

    @Test
    public void testBoolean() {
        CommandResult commandResult = CommandResult.createHelpTabSwitchCommandResult("feedback");
        assertTrue(commandResult.isShowHelp());
        assertFalse(commandResult.isExit());
        assertFalse(commandResult.isShowPlanner());
    }
}
