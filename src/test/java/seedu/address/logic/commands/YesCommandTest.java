package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_NO_CONFIRMATION;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

class YesCommandTest {
    @Test
    public void execute_validTest() {
        YesCommand testCommand = new YesCommand();
        Model model = new ModelManager();
        CommandResult expected = new CommandResult(MESSAGE_NO_CONFIRMATION);
        CommandResult actual = testCommand.execute(model);
        assertEquals(expected, actual);
    }
}
