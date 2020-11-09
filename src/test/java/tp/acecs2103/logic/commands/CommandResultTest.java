package tp.acecs2103.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tp.acecs2103.model.Model;
import tp.acecs2103.model.ModelManager;
import tp.acecs2103.model.UserPrefs;
import tp.acecs2103.testutil.TypicalTasks;

class CommandResultTest {
    @Test
    public void getFeedback_null_string() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        CommandResult commandResult = new ClearCommand().execute(model);
        String messageSuccess = "Task List has been cleared!";
        assertTrue(messageSuccess.equals(commandResult.getFeedbackToUser()));
        assertFalse("random".equals(commandResult.getFeedbackToUser()));
    }
}
