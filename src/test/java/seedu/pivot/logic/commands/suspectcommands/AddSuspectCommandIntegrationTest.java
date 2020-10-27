package seedu.pivot.logic.commands.suspectcommands;

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
import seedu.pivot.model.investigationcase.caseperson.Suspect;
import seedu.pivot.testutil.CaseBuilder;

public class AddSuspectCommandIntegrationTest {
    private static final Name DEFAULT_NAME = new Name("Test Name");
    private static final Gender DEFAULT_GENDER = Gender.createGender("m");
    private static final Phone DEFAULT_PHONE = new Phone("91234567");
    private static final Address DEFAULT_ADDRESS = new Address("Blk 123");
    private static final Email DEFAULT_EMAIL = new Email("abc@gmail.com");
    private static final Suspect DEFAULT_SUSPECT = new Suspect(DEFAULT_NAME, DEFAULT_GENDER,
            DEFAULT_PHONE, DEFAULT_EMAIL, DEFAULT_ADDRESS);
    private Model model = new ModelManager(getTypicalPivot(), new UserPrefs());

    @Test
    public void execute_validIndexAndValidSuspectUnfilteredList_success() {
        StateManager.setState(INDEX_FIRST_PERSON);
        Case caseToUpdate = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        // CASE: ALICE, WITHOUT Suspect
        Case expectedCase = new CaseBuilder(caseToUpdate).withSuspects(DEFAULT_SUSPECT).build();

        AddCommand command = new AddSuspectCommand(INDEX_FIRST_PERSON, DEFAULT_SUSPECT);

        String expectedMessage = String.format(AddSuspectCommand.MESSAGE_ADD_SUSPECT_SUCCESS, DEFAULT_SUSPECT);
        ModelManager expectedModel = new ModelManager((model.getPivot()), new UserPrefs());
        expectedModel.setCase(caseToUpdate, expectedCase);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        StateManager.resetState();
    }

    @Test
    public void execute_validIndexAndDuplicateSuspectUnfilteredList_throwsCommandException() {
        StateManager.setState(INDEX_FIRST_PERSON);
        Case caseToUpdate = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        // CASE: ALICE, WITHOUT Suspect
        Suspect suspect = caseToUpdate.getSuspects().get(0);

        AddCommand command = new AddSuspectCommand(INDEX_FIRST_PERSON, suspect);

        assertCommandFailure(command, model, AddSuspectCommand.MESSAGE_DUPLICATE_SUSPECT);
        StateManager.resetState();
    }

    @Test
    public void execute_validIndexAndValidSuspectFilteredList_success() {
        showCaseAtIndex(model, INDEX_FIRST_PERSON);
        StateManager.setState(INDEX_FIRST_PERSON);
        Case caseToUpdate = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        // CASE: ALICE, WITHOUT Suspect
        Case expectedCase = new CaseBuilder(caseToUpdate).withSuspects(DEFAULT_SUSPECT).build();

        AddCommand command = new AddSuspectCommand(INDEX_FIRST_PERSON, DEFAULT_SUSPECT);

        String expectedMessage = String.format(AddSuspectCommand.MESSAGE_ADD_SUSPECT_SUCCESS, DEFAULT_SUSPECT);
        ModelManager expectedModel = new ModelManager((model.getPivot()), new UserPrefs());
        showCaseAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setCase(caseToUpdate, expectedCase);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        StateManager.resetState();
    }

    @Test
    public void execute_validIndexAndDuplicateSuspectFilteredList_throwsCommandException() {
        showCaseAtIndex(model, INDEX_FIRST_PERSON);
        StateManager.setState(INDEX_FIRST_PERSON);
        Case caseToUpdate = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        // CASE: ALICE, WITHOUT Suspect
        Suspect suspect = caseToUpdate.getSuspects().get(0);

        AddCommand command = new AddSuspectCommand(INDEX_FIRST_PERSON, suspect);

        assertCommandFailure(command, model, AddSuspectCommand.MESSAGE_DUPLICATE_SUSPECT);
        StateManager.resetState();
    }
}
