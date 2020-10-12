package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.history.History;
import seedu.address.model.Model;

/**
 * Undo the last undoable command.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    @Override
    public CommandResult execute(Model model, History history) throws CommandException {
        requireNonNull(model);
        requireNonNull(history);

        return history.undo(model);
    }
}
