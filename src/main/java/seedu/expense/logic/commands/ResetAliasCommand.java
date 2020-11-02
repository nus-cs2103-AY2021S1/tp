package seedu.expense.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.expense.logic.commands.exceptions.CommandException;
import seedu.expense.model.Model;

/**
 * Deletes all existing aliases in the alias map.
 */
public class ResetAliasCommand extends Command {

    public static final String COMMAND_WORD = "resetAlias";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes all custom aliases defined by user.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_EMPTY = "No custom aliases found.";
    public static final String MESSAGE_SUCCESS = "All custom aliases removed.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getAliasMap().isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY);
        }
        model.getAliasMap().removeAllAliases();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof ResetAliasCommand)) {
            return false;
        }
        return true;
    }

}
