package com.eva.logic.commands;

import static com.eva.logic.commands.CommandTestUtil.assertCommandFailure;
import static com.eva.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.eva.logic.commands.ViewCommand.MESSAGE_CHANGE_PANEL;
import static com.eva.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static com.eva.testutil.TypicalPersons.getTypicalApplicantDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalStaffDatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.UserPrefs;
import com.eva.model.current.view.CurrentViewApplicant;
import com.eva.model.current.view.CurrentViewStaff;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.staff.Staff;


class ViewCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
    }

    @Test
    public void execute_viewStaffOnStaffList_showsStaffProfile() {
        expectedModel.setPanelState(PanelState.STAFF_PROFILE);
        Staff staffToView = expectedModel.getFilteredStaffList().get(INDEX_FIRST_PERSON.getZeroBased());
        expectedModel.setCurrentViewStaff(new CurrentViewStaff(staffToView, INDEX_FIRST_PERSON));
        model.setPanelState(PanelState.STAFF_LIST);
        CommandResult expectedResult = new CommandResult(
                String.format(
                        ViewCommand.MESSAGE_VIEW_PERSON_SUCCESS, expectedModel.getPanelState().toString(), staffToView),
                false, false, true);
        assertCommandSuccess(new ViewCommand(INDEX_FIRST_PERSON), model, expectedResult, expectedModel);
    }

    @Test
    public void execute_viewApplicantOnApplicantList_showsApplicantProfile() {
        expectedModel.setPanelState(PanelState.APPLICANT_PROFILE);
        Applicant applicantToView = expectedModel.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        expectedModel.setCurrentViewApplicant(new CurrentViewApplicant(applicantToView, INDEX_FIRST_PERSON));
        model.setPanelState(PanelState.APPLICANT_LIST);
        CommandResult expectedResult = new CommandResult(
                String.format(ViewCommand.MESSAGE_VIEW_PERSON_SUCCESS,
                        expectedModel.getPanelState().toString(), applicantToView),
                false, false, true);
        assertCommandSuccess(new ViewCommand(INDEX_FIRST_PERSON), model, expectedResult, expectedModel);
    }

    @Test
    public void execute_viewStaffOnStaffProfile_throwsCommandException() {
        model.setPanelState(PanelState.STAFF_PROFILE);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_AT_PANEL, MESSAGE_CHANGE_PANEL);
        assertCommandFailure(new ViewCommand(INDEX_FIRST_PERSON), model, expectedMessage);
    }

    @Test
    public void execute_viewApplicantOnApplicantProfile_throwsCommandException() {
        model.setPanelState(PanelState.APPLICANT_PROFILE);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_AT_PANEL, MESSAGE_CHANGE_PANEL);
        assertCommandFailure(new ViewCommand(INDEX_FIRST_PERSON), model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexStaffList_throwsCommandException() {
        model.setPanelState(PanelState.STAFF_LIST);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStaffList().size() + 1);
        assertCommandFailure(new ViewCommand(outOfBoundIndex), model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexApplicantList_throwsCommandException() {
        model.setPanelState(PanelState.APPLICANT_LIST);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        assertCommandFailure(new ViewCommand(outOfBoundIndex), model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

}
