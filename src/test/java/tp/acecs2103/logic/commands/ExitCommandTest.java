package tp.acecs2103.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tp.acecs2103.model.Model;
import tp.acecs2103.model.ModelManager;

public class ExitCommandTest {
    @Test
    public void execute_exit_success() {
        Model model = new ModelManager();
        CommandResult expectedCommandResult =
                new CommandResult(ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertEquals(new ExitCommand().execute(model), expectedCommandResult);
    }
}
