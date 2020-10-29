package seedu.pivot.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_UNDO_SUCCESS = "Undo last command:\n";
    public static final String MESSAGE_UNDO_FAILURE = "You are already at the initial state! "
            + "There is nothing to undo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoPivot()) {
            throw new CommandException(MESSAGE_UNDO_FAILURE);
        }

        model.undoPivot();
        String undoneCommand = model.getStateCommand();

        if (model.isMainPageCommand()) {
            StateManager.resetState();
        }

        return new CommandResult(MESSAGE_UNDO_SUCCESS + undoneCommand);
    }
}
