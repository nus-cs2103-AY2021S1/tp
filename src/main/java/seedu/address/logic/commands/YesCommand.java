package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_NO_CONFIRMATION;

import seedu.address.model.Model;

/**
 * Represents a confirmation.
 */
public class YesCommand extends Command {

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_NO_CONFIRMATION);
    }
}
