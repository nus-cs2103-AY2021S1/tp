package seedu.pivot.logic.commands.suspectcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandFailure;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.showCaseAtIndex;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;
import static seedu.pivot.testutil.TypicalIndexes.FIRST_INDEX;

import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.UserMessages;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.DeleteCommand;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.caseperson.Suspect;


public class DeleteSuspectCommandIntegrationTest {
    private static final Index DEFAULT_SUS_INDEX = Index.fromZeroBased(0);

    private Model model = new ModelManager(getTypicalPivot(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        StateManager.setState(FIRST_INDEX);
        Case caseWithSus = model.getFilteredCaseList().get(FIRST_INDEX.getZeroBased());
        Suspect suspect = caseWithSus.getSuspects().get(0);
        DeleteCommand command = new DeleteSuspectCommand(FIRST_INDEX, DEFAULT_SUS_INDEX);

        assertEquals(String.format(DeleteSuspectCommand.MESSAGE_DELETE_SUSPECT_SUCCESS, suspect),
                command.execute(model).getFeedbackToUser());
        StateManager.resetState();
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        StateManager.setState(FIRST_INDEX);
        Index invalidSusIndex = Index.fromOneBased(model.getFilteredCaseList().get(0).getSuspects().size() + 1);
        DeleteCommand command = new DeleteSuspectCommand(FIRST_INDEX, invalidSusIndex);

        assertCommandFailure(command, model, UserMessages.MESSAGE_INVALID_SUSPECT_DISPLAYED_INDEX);
        StateManager.resetState();
    }

    @Test
    public void execute_validIndexFilteredList_success() throws CommandException {
        showCaseAtIndex(model, FIRST_INDEX); // filter the list
        StateManager.setState(FIRST_INDEX);
        Case caseWithSus = model.getFilteredCaseList().get(FIRST_INDEX.getZeroBased());
        Suspect suspect = caseWithSus.getSuspects().get(0);
        DeleteCommand command = new DeleteSuspectCommand(FIRST_INDEX, DEFAULT_SUS_INDEX);

        assertEquals(String.format(DeleteSuspectCommand.MESSAGE_DELETE_SUSPECT_SUCCESS, suspect),
                command.execute(model).getFeedbackToUser());
        StateManager.resetState();
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCaseAtIndex(model, FIRST_INDEX); // filter the list
        StateManager.setState(FIRST_INDEX);
        Index invalidSusIndex = Index.fromOneBased(model.getFilteredCaseList().get(0).getSuspects().size() + 1);
        DeleteCommand command = new DeleteSuspectCommand(FIRST_INDEX, invalidSusIndex);

        assertCommandFailure(command, model, UserMessages.MESSAGE_INVALID_SUSPECT_DISPLAYED_INDEX);
        StateManager.resetState();
    }
}
