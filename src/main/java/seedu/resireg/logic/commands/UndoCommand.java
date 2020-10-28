package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.storage.Storage;

/**
 * Reverts the {@code model}'s ResiReg to its previous state.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Undo success!";
    public static final String MESSAGE_FAILURE = "No more actions to undo!";

    public static final Help HELP = new Help(COMMAND_WORD, "Undo a previous command");

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoResiReg()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.undoResiReg();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
