package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.history.History;
import seedu.address.model.Model;

/**
 * Redo the last redoable command.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    @Override
    public CommandResult execute(Model model, History history) throws CommandException {
        requireNonNull(model);
        requireNonNull(history);

        return history.redo(model);
    }
}
