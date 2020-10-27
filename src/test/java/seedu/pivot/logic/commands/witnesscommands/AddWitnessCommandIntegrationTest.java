package seedu.pivot.logic.commands.witnesscommands;

import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandFailure;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.showCaseAtIndex;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.pivot.logic.commands.AddCommand;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.caseperson.Address;
import seedu.pivot.model.investigationcase.caseperson.Email;
import seedu.pivot.model.investigationcase.caseperson.Gender;
import seedu.pivot.model.investigationcase.caseperson.Name;
import seedu.pivot.model.investigationcase.caseperson.Phone;
import seedu.pivot.model.investigationcase.caseperson.Witness;
import seedu.pivot.testutil.CaseBuilder;

public class AddWitnessCommandIntegrationTest {
    private static final Name DEFAULT_NAME = new Name("Test Name");
    private static final Gender DEFAULT_GENDER = Gender.createGender("m");
    private static final Phone DEFAULT_PHONE = new Phone("91234567");
    private static final Address DEFAULT_ADDRESS = new Address("Blk 123");
    private static final Email DEFAULT_EMAIL = new Email("abc@gmail.com");
    private static final Witness DEFAULT_WITNESS = new Witness(DEFAULT_NAME, DEFAULT_GENDER,
            DEFAULT_PHONE, DEFAULT_EMAIL, DEFAULT_ADDRESS);
    private Model model = new ModelManager(getTypicalPivot(), new UserPrefs());

    @Test
    public void execute_validIndexAndValidWitnessUnfilteredList_success() {
        StateManager.setState(INDEX_FIRST_PERSON);
        Case caseToUpdate = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        // CASE: ALICE, WITHOUT Witness
        Case expectedCase = new CaseBuilder(caseToUpdate).withWitnesses(DEFAULT_WITNESS).build();

        AddCommand command = new AddWitnessCommand(INDEX_FIRST_PERSON, DEFAULT_WITNESS);

        String expectedMessage = String.format(AddWitnessCommand.MESSAGE_ADD_WITNESS_SUCCESS, DEFAULT_WITNESS);
        ModelManager expectedModel = new ModelManager((model.getPivot()), new UserPrefs());
        expectedModel.setCase(caseToUpdate, expectedCase);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        StateManager.resetState();
    }

    @Test
    public void execute_validIndexAndDuplicateWitnessUnfilteredList_throwsCommandException() {
        StateManager.setState(INDEX_FIRST_PERSON);
        Case caseToUpdate = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        // CASE: ALICE, WITHOUT Witness
        Witness witness = caseToUpdate.getWitnesses().get(0);

        AddCommand command = new AddWitnessCommand(INDEX_FIRST_PERSON, witness);

        assertCommandFailure(command, model, AddWitnessCommand.MESSAGE_DUPLICATE_WITNESS);
        StateManager.resetState();
    }

    @Test
    public void execute_validIndexAndValidWitnessFilteredList_success() {
        showCaseAtIndex(model, INDEX_FIRST_PERSON);
        StateManager.setState(INDEX_FIRST_PERSON);
        Case caseToUpdate = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        // CASE: ALICE, WITHOUT Witness
        Case expectedCase = new CaseBuilder(caseToUpdate).withWitnesses(DEFAULT_WITNESS).build();

        AddCommand command = new AddWitnessCommand(INDEX_FIRST_PERSON, DEFAULT_WITNESS);

        String expectedMessage = String.format(AddWitnessCommand.MESSAGE_ADD_WITNESS_SUCCESS, DEFAULT_WITNESS);
        ModelManager expectedModel = new ModelManager((model.getPivot()), new UserPrefs());
        showCaseAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setCase(caseToUpdate, expectedCase);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        StateManager.resetState();
    }

    @Test
    public void execute_validIndexAndDuplicateWitnessFilteredList_throwsCommandException() {
        showCaseAtIndex(model, INDEX_FIRST_PERSON);
        StateManager.setState(INDEX_FIRST_PERSON);
        Case caseToUpdate = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        // CASE: ALICE, WITHOUT Witness
        Witness witness = caseToUpdate.getWitnesses().get(0);

        AddCommand command = new AddWitnessCommand(INDEX_FIRST_PERSON, witness);

        assertCommandFailure(command, model, AddWitnessCommand.MESSAGE_DUPLICATE_WITNESS);
        StateManager.resetState();
    }
}
