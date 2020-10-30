package seedu.address.logic.commands.modulelistcommands;

<<<<<<< HEAD:src/test/java/seedu/address/logic/commands/modulelistcommands/ExitCommandTest.java
import static seedu.address.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;
=======
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;
>>>>>>> 8fc69aef933ea85d74823cee227d283d23aefd6e:src/test/java/seedu/address/logic/commands/ExitCommandTest.java

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
<<<<<<< HEAD:src/test/java/seedu/address/logic/commands/modulelistcommands/ExitCommandTest.java
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        CommandTestUtil.assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
=======
        //CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        //assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
>>>>>>> 8fc69aef933ea85d74823cee227d283d23aefd6e:src/test/java/seedu/address/logic/commands/ExitCommandTest.java
    }
}
