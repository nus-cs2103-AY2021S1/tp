package com.eva.logic.commands;

import static com.eva.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static com.eva.logic.commands.CommandTestUtil.VALID_APPLICATION_STATUS;
import static com.eva.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static com.eva.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE;
import static com.eva.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static com.eva.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static com.eva.logic.commands.CommandTestUtil.assertChangePanelCommandSuccess;
import static com.eva.logic.commands.CommandTestUtil.assertCommandFailure;
import static com.eva.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static com.eva.logic.commands.SetApplicationStatusCommand.MESSAGE_WRONG_PANEL;
import static com.eva.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static com.eva.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static com.eva.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static com.eva.testutil.TypicalPersons.getTypicalApplicantDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalStaffDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.model.EvaDatabase;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.UserPrefs;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.testutil.ApplicantBuilder;


/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */

public class SetApplicationStatusCommandTest {

    public static final ApplicationStatus RECEIVED_APPLICATION_STATUS = new ApplicationStatus("received");
    public static final ApplicationStatus PROCESSING_APPLICATION_STATUS = new ApplicationStatus("processing");
    public static final ApplicationStatus ACCEPTED_APPLICATION_STATUS = new ApplicationStatus("accepted");
    public static final ApplicationStatus REJECTED_APPLICATION_STATUS = new ApplicationStatus("rejected");

    private Model model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
            getTypicalApplicantDatabase(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Model expectedModel = new ModelManager(
                new EvaDatabase<>(),
                new EvaDatabase<>(),
                new EvaDatabase<>(),
                new UserPrefs());

        Model testModel = new ModelManager(
                new EvaDatabase<>(),
                new EvaDatabase<>(),
                new EvaDatabase<>(),
                new UserPrefs());
        // Creating 2 different applicant objects with the same fields
        Applicant applicantToChange = new ApplicantBuilder().withName(VALID_NAME_AMY)
                .withAddress(VALID_ADDRESS_AMY).withEmail(VALID_EMAIL_AMY).withPhone(VALID_PHONE_AMY)
                .withInterviewDate(VALID_INTERVIEW_DATE).withApplicationStatus(VALID_APPLICATION_STATUS).build();
        Applicant applicantToTest = new ApplicantBuilder().withName(VALID_NAME_AMY)
                .withAddress(VALID_ADDRESS_AMY).withEmail(VALID_EMAIL_AMY).withPhone(VALID_PHONE_AMY)
                .withInterviewDate(VALID_INTERVIEW_DATE).withApplicationStatus(VALID_APPLICATION_STATUS).build();
        assertEquals(applicantToChange, applicantToTest);
        expectedModel.addApplicant(applicantToChange);
        testModel.addApplicant(applicantToTest);
        testModel.setPanelState(PanelState.APPLICANT_LIST);
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        assertEquals(testModel, expectedModel);

        SetApplicationStatusCommand setApplicationStatusCommand = new SetApplicationStatusCommand(INDEX_FIRST_PERSON,
                PROCESSING_APPLICATION_STATUS);
        String expectedMessage = String.format(SetApplicationStatusCommand.MESSAGE_SUCCESS,
                applicantToChange.getName(), PROCESSING_APPLICATION_STATUS, RECEIVED_APPLICATION_STATUS);
        expectedModel.setApplicationStatus(applicantToChange, PROCESSING_APPLICATION_STATUS);

        assertChangePanelCommandSuccess(setApplicationStatusCommand, testModel, expectedMessage, expectedModel);

    }


    @Test
    public void execute_validIndexUnfilteredWrongListPanel_failure() {
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        SetApplicationStatusCommand setApplicationStatusCommand = new SetApplicationStatusCommand(INDEX_FIRST_PERSON,
                PROCESSING_APPLICATION_STATUS);

        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_AT_PANEL,
                MESSAGE_WRONG_PANEL);

        model.setPanelState(PanelState.STAFF_LIST);

        assertCommandFailure(setApplicationStatusCommand, model, expectedMessage);
    }


    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of eva database list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPersonDatabase().getPersonList().size());
        model.setPanelState(PanelState.APPLICANT_LIST);
        SetApplicationStatusCommand setApplicationStatusCommand = new SetApplicationStatusCommand(outOfBoundIndex,
                ACCEPTED_APPLICATION_STATUS);

        assertCommandFailure(setApplicationStatusCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        SetApplicationStatusCommand setApplicationStatusCommand = new SetApplicationStatusCommand(INDEX_FIRST_PERSON,
                REJECTED_APPLICATION_STATUS);
        SetApplicationStatusCommand setApplicationStatusSecondCommand = new SetApplicationStatusCommand(
                INDEX_SECOND_PERSON, REJECTED_APPLICATION_STATUS);

        // same object -> returns true
        assertTrue(setApplicationStatusCommand.equals(setApplicationStatusCommand));

        // same values -> returns true
        SetApplicationStatusCommand setApplicationStatusCommandCopy = new SetApplicationStatusCommand(
                INDEX_FIRST_PERSON, REJECTED_APPLICATION_STATUS);

        assertTrue(setApplicationStatusCommand.equals(setApplicationStatusCommandCopy));

        // different types -> returns false
        assertFalse(setApplicationStatusCommand.equals(1));

        // null -> returns false
        assertFalse(setApplicationStatusCommand.equals(null));

        // different person -> returns false
        assertFalse(setApplicationStatusCommand.equals(setApplicationStatusSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoApplicant(Model model) {
        model.updateFilteredApplicantList(p -> false);

        assertTrue(model.getFilteredApplicantList().isEmpty());
    }
}
