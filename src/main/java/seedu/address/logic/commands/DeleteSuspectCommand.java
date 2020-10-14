package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CASES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.state.StateManager;
import seedu.address.model.Model;
import seedu.address.model.investigationcase.Case;
import seedu.address.model.investigationcase.Suspect;

public class DeleteSuspectCommand extends DeleteCommand {
    public static final String MESSAGE_DELETE_SUSPECT_SUCCESS = "Deleted Suspect: %1$s";

    public DeleteSuspectCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Case> lastShownList = model.getFilteredCaseList();

        assert(StateManager.atCasePage()) : "Program should be at case page";
        Index index = StateManager.getState();

        Case openCase = lastShownList.get(index.getZeroBased());
        List<Suspect> updatedSuspects = openCase.getSuspects();

        if (targetIndex.getZeroBased() >= updatedSuspects.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SUSPECTS_DISPLAYED_INDEX);
        }

        updatedSuspects.remove(targetIndex.getZeroBased());
        Case editedCase = new Case(openCase.getTitle(), openCase.getDescription(), openCase.getStatus(),
                openCase.getDocuments(), updatedSuspects, openCase.getVictims(), openCase.getWitnesses(),
                openCase.getTags());

        model.setCase(openCase, editedCase);
        model.updateFilteredCaseList(PREDICATE_SHOW_ALL_CASES);
        return new CommandResult(String.format(MESSAGE_DELETE_SUSPECT_SUCCESS, editedCase));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteSuspectCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteSuspectCommand) other).targetIndex)); // state check
    }
}
