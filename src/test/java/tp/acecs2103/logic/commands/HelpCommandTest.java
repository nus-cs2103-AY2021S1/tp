package tp.acecs2103.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tp.acecs2103.model.Model;
import tp.acecs2103.model.ModelManager;
import tp.acecs2103.model.UserPrefs;
import tp.acecs2103.testutil.TypicalTasks;

class HelpCommandTest {
    @Test
    public void execute_null_success() {
        Model model = new ModelManager(TypicalTasks.getTypicalTaskList(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(HelpCommand.SHOWING_HELP_MESSAGE, true, false);
        assertEquals(new HelpCommand().execute(model), expectedCommandResult);
    }
}
