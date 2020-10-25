package seedu.pivot.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.model.Model;

public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_REDO_SUCCESS = "Redo last command";
    public static final String MESSAGE_REDO_FAILURE = "You are already at the most recent edit! "
            + "There is nothing to redo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoPivot()) {
            throw new CommandException(MESSAGE_REDO_FAILURE);
        }

        model.redoPivot();
        return new CommandResult(MESSAGE_REDO_SUCCESS);
    }
}
