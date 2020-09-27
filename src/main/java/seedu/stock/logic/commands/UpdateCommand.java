package seedu.stock.logic.commands;

import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;

public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public UpdateCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateCommand)) {
            return false;
        }

        return false;
    }
}
