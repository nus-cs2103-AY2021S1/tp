package seedu.address.logic.commands.casecommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.investigationcase.Case;

/**
 * Deletes a case identified using it's displayed index from PIVOT.
 */
public class DeleteCaseCommand extends DeleteCommand {

    public static final String MESSAGE_DELETE_CASE_SUCCESS = "Deleted Case: %1$s";

    public DeleteCaseCommand(Index targetIndex) {
        super(targetIndex);
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

}
