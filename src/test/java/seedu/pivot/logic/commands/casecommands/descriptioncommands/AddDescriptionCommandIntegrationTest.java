package seedu.pivot.logic.commands.casecommands.descriptioncommands;

import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandFailure;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.showCaseAtIndex;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;
import static seedu.pivot.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.pivot.testutil.TypicalIndexes.SECOND_INDEX;

import org.junit.jupiter.api.Test;

import seedu.pivot.logic.commands.AddCommand;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.Pivot;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.Description;
import seedu.pivot.testutil.CaseBuilder;


/**
 * Contains Integration tests (interactions with the Model and State) for {@Code AddDescriptionCommand}
 */
public class AddDescriptionCommandIntegrationTest {
    private Model model = new ModelManager(getTypicalPivot(), new UserPrefs());

    @Test
    public void execute_validIndexAndValidDescriptionUnfilteredList_success() {
        StateManager.setState(FIRST_INDEX);
        Case caseToUpdate = model.getFilteredCaseList().get(FIRST_INDEX.getZeroBased());
        // CASE: ALICE_PAULINE_ASSAULT, WITHOUT DESCRIPTIONS
        Case expectedCase = new CaseBuilder(caseToUpdate).withDescription("New Description").build();
        Description description = new Description("New Description");

        AddDescriptionCommand command = new AddDescriptionCommand(FIRST_INDEX, description);

        String expectedMessage = String.format(AddDescriptionCommand.MESSAGE_ADD_DESCRIPTION_SUCCESS, description);
        ModelManager expectedModel = new ModelManager(new Pivot(model.getPivot()), new UserPrefs());
        expectedModel.setCase(caseToUpdate, expectedCase);
        expectedModel.commitPivot(expectedMessage, command);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        StateManager.resetState();
    }

    @Test
    public void execute_validIndexWithExistingDescriptionUnfilteredList_throwsCommandException() {
        StateManager.setState(SECOND_INDEX);
        Case caseToUpdate = model.getFilteredCaseList().get(SECOND_INDEX.getZeroBased());
        // CASE: BENSON_MEIER_ROBBERY, WITH DESCRIPTIONS
        Description description = caseToUpdate.getDescription();

        AddCommand command = new AddDescriptionCommand(SECOND_INDEX, description);

        assertCommandFailure(command, model, AddDescriptionCommand.MESSAGE_DESCRIPTION_ALREADY_EXISTS);
        StateManager.resetState();
    }

    @Test
    public void execute_validIndexAndValidDescriptionFilteredList_success() {
        showCaseAtIndex(model, FIRST_INDEX);
        StateManager.setState(FIRST_INDEX);
        Case caseToUpdate = model.getFilteredCaseList().get(FIRST_INDEX.getZeroBased());
        // CASE: ALICE, WITHOUT DESCRIPTIONS
        Case expectedCase = new CaseBuilder(caseToUpdate).withDescription("New Description").build();
        Description description = new Description("New Description");

        AddDescriptionCommand command = new AddDescriptionCommand(FIRST_INDEX, description);

        String expectedMessage = String.format(AddDescriptionCommand.MESSAGE_ADD_DESCRIPTION_SUCCESS, description);
        ModelManager expectedModel = new ModelManager(new Pivot(model.getPivot()), new UserPrefs());
        showCaseAtIndex(expectedModel, FIRST_INDEX);
        expectedModel.setCase(caseToUpdate, expectedCase);
        expectedModel.commitPivot(expectedMessage, command);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        StateManager.resetState();
    }
}
