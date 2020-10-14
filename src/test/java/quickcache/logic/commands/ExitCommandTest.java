package quickcache.logic.commands;

import org.junit.jupiter.api.Test;

import quickcache.model.Model;
import quickcache.model.ModelManager;

public class ExitCommandTest {
    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(
            ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false);
        CommandTestUtil.assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
