package seedu.pivot.logic.commands.witnesscommands;

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
import seedu.pivot.model.investigationcase.caseperson.Witness;

public class DeleteWitnessCommandIntegrationTest {
    private static final Index DEFAULT_WITNESS_INDEX = Index.fromZeroBased(0);

    private Model model = new ModelManager(getTypicalPivot(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        StateManager.setState(FIRST_INDEX);
        Case caseWithWitness = model.getFilteredCaseList().get(FIRST_INDEX.getZeroBased());
        Witness witness = caseWithWitness.getWitnesses().get(0);
        DeleteCommand command = new DeleteWitnessCommand(FIRST_INDEX, DEFAULT_WITNESS_INDEX);

        assertEquals(String.format(DeleteWitnessCommand.MESSAGE_DELETE_WITNESS_SUCCESS, witness),
                command.execute(model).getFeedbackToUser());
        StateManager.resetState();
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        StateManager.setState(FIRST_INDEX);
        Index invalidWitnessIndex = Index.fromOneBased(model.getFilteredCaseList().get(0).getWitnesses().size() + 1);
        DeleteCommand command = new DeleteWitnessCommand(FIRST_INDEX, invalidWitnessIndex);

        assertCommandFailure(command, model, UserMessages.MESSAGE_INVALID_WITNESS_DISPLAYED_INDEX);
        StateManager.resetState();
    }

    @Test
    public void execute_validIndexFilteredList_success() throws CommandException {
        showCaseAtIndex(model, FIRST_INDEX); // filter the list
        StateManager.setState(FIRST_INDEX);
        Case caseWithWitness = model.getFilteredCaseList().get(FIRST_INDEX.getZeroBased());
        Witness witness = caseWithWitness.getWitnesses().get(0);
        DeleteCommand command = new DeleteWitnessCommand(FIRST_INDEX, DEFAULT_WITNESS_INDEX);

        assertEquals(String.format(DeleteWitnessCommand.MESSAGE_DELETE_WITNESS_SUCCESS, witness),
                command.execute(model).getFeedbackToUser());
        StateManager.resetState();
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCaseAtIndex(model, FIRST_INDEX); // filter the list
        StateManager.setState(FIRST_INDEX);
        Index invalidWitnessIndex = Index.fromOneBased(model.getFilteredCaseList().get(0).getWitnesses().size() + 1);
        DeleteCommand command = new DeleteWitnessCommand(FIRST_INDEX, invalidWitnessIndex);

        assertCommandFailure(command, model, UserMessages.MESSAGE_INVALID_WITNESS_DISPLAYED_INDEX);
        StateManager.resetState();
    }
}
