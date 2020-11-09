package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CLEAR_SUCCESS;

import seedu.address.model.GradPad;
import seedu.address.model.Model;

/**
 * Clears the GradPad.
 */
public class ClearCommand extends Command {

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setGradPad(new GradPad());
        return new CommandResult(MESSAGE_CLEAR_SUCCESS);
    }

    @Override
    public boolean requiresStall() {
        return true;
    }
}
