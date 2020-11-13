package seedu.jarvis.logic.commands;

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
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());
    }

    // Tests for the tab switching feature
    @Test
    public void getCommandTargetFeature_missions_correctTargetFeature() {
        CommandResult commandResult = new CommandResult("success", CommandTargetFeature.Missions);
        CommandTargetFeature commandTargetFeature = commandResult.getCommandTargetFeature();
        assertEquals(commandTargetFeature, CommandTargetFeature.Missions);
    }

    @Test
    public void getCommandTargetFeature_quests_correctTargetFeature() {
        CommandResult commandResult = new CommandResult("success", CommandTargetFeature.Quest);
        CommandTargetFeature commandTargetFeature = commandResult.getCommandTargetFeature();
        assertEquals(commandTargetFeature, CommandTargetFeature.Quest);
    }

    @Test
    public void getCommandTargetFeature_consultations_correctTargetFeature() {
        CommandResult commandResult = new CommandResult("success", CommandTargetFeature.Consultations);
        CommandTargetFeature commandTargetFeature = commandResult.getCommandTargetFeature();
        assertEquals(commandTargetFeature, CommandTargetFeature.Consultations);
    }

    @Test
    public void getCommandTargetFeature_masteryChecks_correctTargetFeature() {
        CommandResult commandResult = new CommandResult("success", CommandTargetFeature.MasteryCheck);
        CommandTargetFeature commandTargetFeature = commandResult.getCommandTargetFeature();
        assertEquals(commandTargetFeature, CommandTargetFeature.MasteryCheck);
    }

    @Test
    public void getCommandTargetFeature_tasks_correctTargetFeature() {
        CommandResult commandResult = new CommandResult("success", CommandTargetFeature.Tasks);
        CommandTargetFeature commandTargetFeature = commandResult.getCommandTargetFeature();
        assertEquals(commandTargetFeature, CommandTargetFeature.Tasks);
    }

    @Test
    public void getCommandTargetFeature_noTargetFeature_notAssigned() {
        CommandResult commandResult = new CommandResult("success");
        CommandTargetFeature commandTargetFeature = commandResult.getCommandTargetFeature();
        assertEquals(commandTargetFeature, CommandTargetFeature.NotAssigned);
    }
}
