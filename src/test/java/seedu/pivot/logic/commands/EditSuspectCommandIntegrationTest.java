package seedu.pivot.logic.commands;

import static seedu.pivot.commons.core.UserMessages.MESSAGE_DUPLICATE_SUSPECT;
import static seedu.pivot.logic.commands.suspectcommands.EditSuspectCommand.MESSAGE_EDIT_SUSPECT_SUCCESS;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandFailure;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.pivot.testutil.CasePersonBuilder.DEFAULT_EMAIL;
import static seedu.pivot.testutil.CasePersonBuilder.DEFAULT_NAME;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;
import static seedu.pivot.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.pivot.testutil.TypicalIndexes.SECOND_INDEX;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.UserMessages;
import seedu.pivot.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.pivot.logic.commands.suspectcommands.EditSuspectCommand;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.Pivot;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.caseperson.Suspect;
import seedu.pivot.testutil.CaseBuilder;
import seedu.pivot.testutil.CasePersonBuilder;
import seedu.pivot.testutil.EditPersonDescriptorBuilder;

public class EditSuspectCommandIntegrationTest {

    private Model model = new ModelManager(getTypicalPivot(), new UserPrefs());

    @BeforeEach
    void setUp() {
        StateManager.setState(FIRST_INDEX);
    }

    @AfterAll
    static void tearDown() {
        StateManager.resetState();
    }

    @Test
    public void execute_allFieldsSpecified_success() {
        Suspect editedSuspect = new CasePersonBuilder().buildSuspect();
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder(editedSuspect).build();
        EditSuspectCommand editSuspectCommand = new EditSuspectCommand(FIRST_INDEX, FIRST_INDEX, editPersonDescriptor);

        Case caseToEdit = model.getFilteredCaseList().get(FIRST_INDEX.getZeroBased());
        Case editedCase = new CaseBuilder(caseToEdit).withSuspects(editedSuspect).build();

        String expectedMessage = String.format(MESSAGE_EDIT_SUSPECT_SUCCESS, editedSuspect);

        Model expectedModel = new ModelManager(new Pivot(model.getPivot()), new UserPrefs());
        expectedModel.setCase(caseToEdit, editedCase);
        expectedModel.commitPivot(expectedMessage, editSuspectCommand);

        assertCommandSuccess(editSuspectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withName(DEFAULT_NAME).withEmail(DEFAULT_EMAIL).build();
        EditSuspectCommand editSuspectCommand = new EditSuspectCommand(FIRST_INDEX, FIRST_INDEX, editPersonDescriptor);

        Case caseToEdit = model.getFilteredCaseList().get(FIRST_INDEX.getZeroBased());
        Suspect suspectToEdit = caseToEdit.getSuspects().get(FIRST_INDEX.getZeroBased());
        Suspect editedSuspect = new CasePersonBuilder(suspectToEdit)
                .withName(DEFAULT_NAME).withEmail(DEFAULT_EMAIL).buildSuspect();
        Case editedCase = new CaseBuilder(caseToEdit).withSuspects(editedSuspect).build();

        String expectedMessage = String.format(MESSAGE_EDIT_SUSPECT_SUCCESS, editedSuspect);

        Model expectedModel = new ModelManager(new Pivot(model.getPivot()), new UserPrefs());
        expectedModel.setCase(caseToEdit, editedCase);
        expectedModel.commitPivot(expectedMessage, editSuspectCommand);

        assertCommandSuccess(editSuspectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePerson_failure() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder().build();
        EditSuspectCommand editSuspectCommand = new EditSuspectCommand(FIRST_INDEX, FIRST_INDEX, editPersonDescriptor);

        String expectedMessage = String.format(MESSAGE_DUPLICATE_SUSPECT);

        assertCommandFailure(editSuspectCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidPersonIndex_failure() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withName(DEFAULT_NAME).withEmail(DEFAULT_EMAIL).build();
        EditSuspectCommand editSuspectCommand = new EditSuspectCommand(FIRST_INDEX, SECOND_INDEX, editPersonDescriptor);

        assertCommandFailure(editSuspectCommand, model, UserMessages.MESSAGE_INVALID_SUSPECT_DISPLAYED_INDEX);
    }
}
