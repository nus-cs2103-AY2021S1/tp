package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.storage.Storage;

/**
 * Reverts the {@code model}'s ResiReg to its previously undone state.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_SUCCESS = "Redo success!";
    public static final String MESSAGE_FAILURE = "No more actions to redo!";

    public static final Help HELP = new Help(COMMAND_WORD, "Redo a previous command.");

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoResiReg()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.redoResiReg();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
