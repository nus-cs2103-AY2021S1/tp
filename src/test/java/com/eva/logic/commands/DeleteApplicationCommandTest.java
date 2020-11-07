package com.eva.logic.commands;

import static com.eva.logic.commands.CommandTestUtil.assertCommandFailure;
import static com.eva.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.eva.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static com.eva.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static com.eva.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static com.eva.testutil.TypicalPersons.getTypicalApplicantDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static com.eva.testutil.staff.TypicalStaffs.getTypicalStaffDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.UserPrefs;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.application.Application;

class DeleteApplicationCommandTest {

    private Model model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
            getTypicalApplicantDatabase(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        model.setPanelState(PanelState.APPLICANT_LIST);
        Applicant target = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteApplicationCommand deleteApplicationCommand = new DeleteApplicationCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteApplicationCommand.MESSAGE_DELETE_APPLICATION_SUCCESS, target);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);
        ModelManager expectedModel = new ModelManager(model.getPersonDatabase(), model.getStaffDatabase(),
                model.getApplicantDatabase(), new UserPrefs());
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        expectedModel.deleteApplication(target, new Application());

        assertCommandSuccess(deleteApplicationCommand, model, expectedResult, expectedModel);

    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        model.setPanelState(PanelState.APPLICANT_LIST);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        DeleteApplicationCommand deleteApplicationCommand = new DeleteApplicationCommand(outOfBoundIndex);

        assertCommandFailure(deleteApplicationCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);
        model.setPanelState(PanelState.APPLICANT_LIST);
        Applicant target = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteApplicationCommand deleteApplicationCommand = new DeleteApplicationCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteApplicationCommand.MESSAGE_DELETE_APPLICATION_SUCCESS, target);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);

        Model expectedModel = new ModelManager(model.getPersonDatabase(), model.getStaffDatabase(),
                model.getApplicantDatabase(), new UserPrefs());
        expectedModel.deleteApplication(target, new Application());
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);

        assertCommandSuccess(deleteApplicationCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void equals() {
        DeleteApplicationCommand deleteApplicationCommand1 = new DeleteApplicationCommand(INDEX_FIRST_PERSON);
        DeleteApplicationCommand deleteApplicationCommand2 = new DeleteApplicationCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertEquals(deleteApplicationCommand1, deleteApplicationCommand1);

        // same values -> returns true
        DeleteApplicationCommand deleteApplicationCommand1Copy = new DeleteApplicationCommand(INDEX_FIRST_PERSON);
        assertEquals(deleteApplicationCommand1, deleteApplicationCommand1Copy);

        // different types -> returns false
        assertNotEquals(deleteApplicationCommand1, 1);

        // null -> returns false
        assertNotEquals(deleteApplicationCommand1, null);

        // different index -> returns false
        assertNotEquals(deleteApplicationCommand2, deleteApplicationCommand1);
    }
}
