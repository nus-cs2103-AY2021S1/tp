package seedu.pivot.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.investigationcase.Case;

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

        String stateCommand = model.undoPivot();

        List<Case> lastShownList = model.getFilteredCaseList();
        if (StateManager.atCasePage()) {
            if (StateManager.getState().getZeroBased() >= lastShownList.size()) {
                StateManager.resetState();
            }
        }

        return new CommandResult(MESSAGE_UNDO_SUCCESS + stateCommand);
    }
}
