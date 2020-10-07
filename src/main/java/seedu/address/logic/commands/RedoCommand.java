package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Redoes the previously undone command.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_SUCCESS = "Redo-ed the previous command";

    public static final String MESSAGE_NO_MORE_COMMANDS = "No more commands to redo";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canRedoCliniCal()) {
            throw new CommandException(MESSAGE_NO_MORE_COMMANDS);
        }
        model.redoCliniCal();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
