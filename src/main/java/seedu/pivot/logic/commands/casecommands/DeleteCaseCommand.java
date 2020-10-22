package seedu.pivot.logic.commands.casecommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.pivot.commons.core.Messages;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.CommandResult;
import seedu.pivot.logic.commands.DeleteCommand;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.model.Model;
import seedu.pivot.model.investigationcase.Case;

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
