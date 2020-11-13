package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false,
                false, false, false, false, false, 0,
                false, false, 0, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false,
                false, false, false, false, false, 0,
                false, false, 0, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true,
                false, false, false, false, false, 0,
                false, false, 0, false)));

        //different showAllStats value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false,
                true, false, false, false, false, 0,
                false, false, 0, false)));

        //different showCalorieStats value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false,
                false, true, false, false, false, 0,
                false, false, 0, false)));

        //different showWeightStats value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false,
                false, false, true, false, false, 0,
                false, false, 0, false)));

        //different clear value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false,
                false, false, false, true, false, 0,
                false, false, 0, false)));

        //different delete value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false,
                false, false, false, false, true, 1,
                false, false, 0, false)));

        //different indexDelete value -> return false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false,
                false, false, false, false, false, 1,
                false, false, 0, false)));

        //different view value -> return false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false,
                false, false, false, false, false, 0,
                false, true, 0, false)));

        //different indexView value -> return false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false,
                false, false, false, false, false, 0,
                false, false, 1, false)));

        //different showCommanderStats value -> return false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false,
                false, false, false, false, false, 0,
                false, false, 0, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", true, false, false,
                        false, false, false, false, 0,
                        false, false, 0, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, true, false,
                        false, false, false, false, 0,
                        false, false, 0, false).hashCode());

        //different showAllStats value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, false, true,
                        false, false, false, false, 0,
                        false, false, 0, false).hashCode());

        //different showCalorieStats value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, false, false,
                        true, false, false, false, 0,
                        false, false, 0, false).hashCode());

        //different showWeightStats value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, false, false,
                        false, true, false, false, 0,
                        false, false, 0, false).hashCode());

        //different clear value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, false, false,
                        false, false, true, false, 0,
                        false, false, 0, false).hashCode());

        //different delete value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, false, false,
                        false, false, false, true, 0,
                        false, false, 0, false).hashCode());

        //different indexDelete value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, false, false,
                        false, false, false, false, 1,
                        false, false, 0, false).hashCode());

        //different view value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, false, false,
                        false, false, false, false, 0,
                        false, true, 0, false).hashCode());

        //different indexView value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, false, false,
                        false, false, false, false, 0,
                        false, false, 1, false).hashCode());

    }
}
