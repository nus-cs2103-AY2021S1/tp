package seedu.address.logic.commands.global;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXTRA_ARGS = "Please do not provide extra arguments after \""
        + COMMAND_WORD + "\"";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Main Catalogue as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
