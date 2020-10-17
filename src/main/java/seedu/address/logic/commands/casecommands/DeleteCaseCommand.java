package seedu.address.logic.commands.casecommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.state.StateManager;
import seedu.address.model.Model;
import seedu.address.model.investigationcase.Case;

/**
 * Deletes a case identified using it's displayed index from PIVOT.
 */
public class DeleteCaseCommand extends DeleteCommand {

    public static final String MESSAGE_DELETE_CASE_SUCCESS = "Deleted Case: %1$s";

    private final Index targetIndex;

    public DeleteCaseCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Case> lastShownList = model.getFilteredCaseList();

        assert(StateManager.atMainPage()) : "Program should be at main page";

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
        }

        Case caseToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteCase(caseToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_CASE_SUCCESS, caseToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCaseCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCaseCommand) other).targetIndex)); // state check
    }
}
