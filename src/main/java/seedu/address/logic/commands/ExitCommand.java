package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.account.ActiveAccount;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Common Cents as requested ... Good bye.";

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) {
        return CommandResultFactory.createCommandResultForExitCommand(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

}
