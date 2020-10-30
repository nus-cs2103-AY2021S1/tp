package seedu.address.logic.commands.modulelistcommands;

//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
<<<<<<< HEAD:src/test/java/seedu/address/logic/commands/modulelistcommands/HelpCommandTest.java
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;
=======
//import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;
>>>>>>> 8fc69aef933ea85d74823cee227d283d23aefd6e:src/test/java/seedu/address/logic/commands/HelpCommandTest.java

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
<<<<<<< HEAD:src/test/java/seedu/address/logic/commands/modulelistcommands/HelpCommandTest.java
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        CommandTestUtil.assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
=======
        //CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        //assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
>>>>>>> 8fc69aef933ea85d74823cee227d283d23aefd6e:src/test/java/seedu/address/logic/commands/HelpCommandTest.java
    }
}
