package seedu.tasklist.logic.commands;

import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.logic.commands.exceptions.CommandException;
import seedu.tasklist.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    public String userInput;

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_INCORRECT_FORMAT = "Invalid command format! \nFormat: exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting ProductiveNus as requested ...";

    public ExitCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        boolean hasNoArgument = userInput.trim().contentEquals("exit");
        if (hasNoArgument) {
            return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        } else {
            throw new CommandException(MESSAGE_INCORRECT_FORMAT);
        }
    }

}
