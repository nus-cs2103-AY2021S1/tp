package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.state.StateManager;
import seedu.address.model.Model;
import seedu.address.model.investigationcase.Case;

public class OpenCaseCommand extends OpenCommand {

    public static final String MESSAGE_OPEN_CASE_SUCCESS = "Opened Case: %1$s";

    public OpenCaseCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Case> lastShownList = model.getFilteredCaseList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
        }

        Case caseToOpen = lastShownList.get(targetIndex.getZeroBased());

        StateManager.setState(targetIndex);

        return new CommandResult(String.format(MESSAGE_OPEN_CASE_SUCCESS, caseToOpen));
    }
}
