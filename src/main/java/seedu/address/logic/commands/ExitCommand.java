package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, true);
    }
}
