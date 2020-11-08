package seedu.pivot.logic.commands;

import static seedu.pivot.commons.core.UserMessages.MESSAGE_DUPLICATE_WITNESS;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandFailure;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.showCaseAtIndex;
import static seedu.pivot.logic.commands.witnesscommands.EditWitnessCommand.MESSAGE_EDIT_WITNESS_SUCCESS;
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
import seedu.pivot.logic.commands.witnesscommands.EditWitnessCommand;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.Pivot;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.caseperson.Witness;
import seedu.pivot.testutil.CaseBuilder;
import seedu.pivot.testutil.CasePersonBuilder;
import seedu.pivot.testutil.EditPersonDescriptorBuilder;

public class EditWitnessCommandIntegrationTest {
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
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Witness editedWitness = new CasePersonBuilder().buildWitness();
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder(editedWitness).build();
        EditWitnessCommand editWitnessCommand = new EditWitnessCommand(FIRST_INDEX, FIRST_INDEX, editPersonDescriptor);

        Case caseToEdit = model.getFilteredCaseList().get(FIRST_INDEX.getZeroBased());
        Case editedCase = new CaseBuilder(caseToEdit).withWitnesses(editedWitness).build();

        String expectedMessage = String.format(MESSAGE_EDIT_WITNESS_SUCCESS, editedWitness);

        Model expectedModel = new ModelManager(new Pivot(model.getPivot()), new UserPrefs());
        expectedModel.setCase(caseToEdit, editedCase);
        expectedModel.commitPivot(expectedMessage, editWitnessCommand);

        assertCommandSuccess(editWitnessCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedFilteredList_success() {
        showCaseAtIndex(model, FIRST_INDEX);

        Witness editedWitness = new CasePersonBuilder().buildWitness();
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder(editedWitness).build();
        EditWitnessCommand editWitnessCommand = new EditWitnessCommand(FIRST_INDEX, FIRST_INDEX, editPersonDescriptor);

        Case caseToEdit = model.getFilteredCaseList().get(FIRST_INDEX.getZeroBased());
        Case editedCase = new CaseBuilder(caseToEdit).withWitnesses(editedWitness).build();

        String expectedMessage = String.format(MESSAGE_EDIT_WITNESS_SUCCESS, editedWitness);

        Model expectedModel = new ModelManager(new Pivot(model.getPivot()), new UserPrefs());
        expectedModel.setCase(caseToEdit, editedCase);
        showCaseAtIndex(expectedModel, FIRST_INDEX);
        expectedModel.commitPivot(expectedMessage, editWitnessCommand);

        assertCommandSuccess(editWitnessCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withName(DEFAULT_NAME).withEmail(DEFAULT_EMAIL).build();
        EditWitnessCommand editWitnessCommand = new EditWitnessCommand(FIRST_INDEX, FIRST_INDEX, editPersonDescriptor);

        Case caseToEdit = model.getFilteredCaseList().get(FIRST_INDEX.getZeroBased());
        Witness witnessToEdit = caseToEdit.getWitnesses().get(FIRST_INDEX.getZeroBased());
        Witness editedWitness = new CasePersonBuilder(witnessToEdit)
                .withName(DEFAULT_NAME).withEmail(DEFAULT_EMAIL).buildWitness();
        Case editedCase = new CaseBuilder(caseToEdit).withWitnesses(editedWitness).build();

        String expectedMessage = String.format(MESSAGE_EDIT_WITNESS_SUCCESS, editedWitness);

        Model expectedModel = new ModelManager(new Pivot(model.getPivot()), new UserPrefs());
        expectedModel.setCase(caseToEdit, editedCase);
        expectedModel.commitPivot(expectedMessage, editWitnessCommand);

        assertCommandSuccess(editWitnessCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedFilteredList_success() {
        showCaseAtIndex(model, FIRST_INDEX);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withName(DEFAULT_NAME).withEmail(DEFAULT_EMAIL).build();
        EditWitnessCommand editWitnessCommand = new EditWitnessCommand(FIRST_INDEX, FIRST_INDEX, editPersonDescriptor);

        Case caseToEdit = model.getFilteredCaseList().get(FIRST_INDEX.getZeroBased());
        Witness witnessToEdit = caseToEdit.getWitnesses().get(FIRST_INDEX.getZeroBased());
        Witness editedWitness = new CasePersonBuilder(witnessToEdit)
                .withName(DEFAULT_NAME).withEmail(DEFAULT_EMAIL).buildWitness();
        Case editedCase = new CaseBuilder(caseToEdit).withWitnesses(editedWitness).build();

        String expectedMessage = String.format(MESSAGE_EDIT_WITNESS_SUCCESS, editedWitness);

        Model expectedModel = new ModelManager(new Pivot(model.getPivot()), new UserPrefs());
        expectedModel.setCase(caseToEdit, editedCase);
        showCaseAtIndex(expectedModel, FIRST_INDEX);
        expectedModel.commitPivot(expectedMessage, editWitnessCommand);

        assertCommandSuccess(editWitnessCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder().build();
        EditWitnessCommand editWitnessCommand = new EditWitnessCommand(FIRST_INDEX, FIRST_INDEX, editPersonDescriptor);

        String expectedMessage = String.format(MESSAGE_DUPLICATE_WITNESS);

        assertCommandFailure(editWitnessCommand, model, expectedMessage);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showCaseAtIndex(model, FIRST_INDEX);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder().build();
        EditWitnessCommand editWitnessCommand = new EditWitnessCommand(FIRST_INDEX, FIRST_INDEX, editPersonDescriptor);

        String expectedMessage = String.format(MESSAGE_DUPLICATE_WITNESS);

        assertCommandFailure(editWitnessCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withName(DEFAULT_NAME).withEmail(DEFAULT_EMAIL).build();
        EditWitnessCommand editWitnessCommand = new EditWitnessCommand(FIRST_INDEX, SECOND_INDEX, editPersonDescriptor);

        assertCommandFailure(editWitnessCommand, model, UserMessages.MESSAGE_INVALID_WITNESS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showCaseAtIndex(model, FIRST_INDEX);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withName(DEFAULT_NAME).withEmail(DEFAULT_EMAIL).build();
        EditWitnessCommand editWitnessCommand = new EditWitnessCommand(FIRST_INDEX, SECOND_INDEX, editPersonDescriptor);

        assertCommandFailure(editWitnessCommand, model, UserMessages.MESSAGE_INVALID_WITNESS_DISPLAYED_INDEX);
    }
}
