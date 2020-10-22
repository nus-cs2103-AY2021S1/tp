package seedu.pivot.logic.commands.casecommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.pivot.commons.core.Messages;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.CommandResult;
import seedu.pivot.logic.commands.OpenCommand;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.investigationcase.Case;

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
