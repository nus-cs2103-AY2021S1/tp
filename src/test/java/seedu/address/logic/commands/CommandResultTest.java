package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {

    @Test
    public void booleanExpressions_true() {
        // show help true -> returns true for isShowHelp()
        assertTrue(new CommandResult("feedback",
                true, false, false, false, false, false).isShowHelp());

        // exit true -> returns true for isExit()
        assertTrue(new CommandResult("feedback",
                false, true, false, false, false, false).isExit());

        // sortTag true -> returns true for isSortTag()
        assertTrue(new CommandResult("feedback",
                false, false, true, false, false, false).isSortTag());

        // sortPerson true -> returns true for isSortPerson()
        assertTrue(new CommandResult("feedback",
                false, false, false, true, false, false).isSortPerson());

        // tag list true -> returns true for isTagList()
        assertTrue(new CommandResult("feedback",
                false, false, false, false, true, false).isTagList());

        // person list true -> returns true for isPersonList()
        assertTrue(new CommandResult("feedback",
                false, false, false, false, false, true).isPersonList());

        // show help false -> returns false for isShowHelp()
        assertFalse(new CommandResult("feedback",
                false, false, false, false, false, false).isShowHelp());

        // exit false -> returns false for isExit()
        assertFalse(new CommandResult("feedback",
                false, false, false, false, false, false).isExit());

        // sort Person false -> returns false for isSortPerson()
        assertFalse(new CommandResult("feedback",
                false, false, false, false, false, false).isSortPerson());

        // tag list false -> returns false for isTagList()
        assertFalse(new CommandResult("feedback",
                false, false, false, false, false, false).isTagList());

        // person list false -> returns false for isPersonList()
        assertFalse(new CommandResult("feedback",
                false, false, false, false, false, false).isPersonList());
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback",
                false, false, false, false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                true, false, false, false, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                false, true, false, false, false, false)));

        // different sortTag value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                false, false, true, false, true, false)));

        // different sortPerson value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                false, false, false, true, false, true)));

        //different isTagList value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                false, false, false, false, true, false)));

        //different isPersonList value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                false, false, false, false, false, true)));

    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                true, false, false, false, false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, true, false, false, false, false).hashCode());

        // different sortTag value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, true, false, false, false).hashCode());

        // different sortPerson value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, false, true, false, false).hashCode());

        // different isTagList value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, false, false, true, false).hashCode());

        // different isPersonList value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, false, false, false, true).hashCode());
    }
}
