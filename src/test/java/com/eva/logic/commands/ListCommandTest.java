package com.eva.logic.commands;


import static com.eva.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.eva.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static com.eva.logic.commands.CommandTestUtil.showStaffAtIndex;
import static com.eva.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static com.eva.testutil.TypicalPersons.getTypicalApplicantDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalStaffDatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.eva.commons.core.PanelState;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

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
    public void execute_staffListIsNotFilteredOnStaffList_showsSameList() {
        expectedModel.setPanelState(PanelState.STAFF_LIST);
        model.setPanelState(PanelState.STAFF_LIST);
        CommandResult expectedResult = new CommandResult(
                ListCommand.MESSAGE_SUCCESS_STAFF, false, false, true);
        assertCommandSuccess(new ListCommand(true), model, expectedResult, expectedModel);
    }

    @Test
    public void execute_staffListIsFilteredOnStaffList_showsEverything() {
        showStaffAtIndex(model, INDEX_FIRST_PERSON);
        expectedModel.setPanelState(PanelState.STAFF_LIST);
        model.setPanelState(PanelState.STAFF_LIST);
        CommandResult expectedResult = new CommandResult(
                ListCommand.MESSAGE_SUCCESS_STAFF, false, false, true);
        assertCommandSuccess(new ListCommand(true), model, expectedResult, expectedModel);
    }

    @Test
    public void execute_staffListIsFilteredOnStaffProfile_showsEverything() {
        showStaffAtIndex(model, INDEX_FIRST_PERSON);
        expectedModel.setPanelState(PanelState.STAFF_LIST);
        model.setPanelState(PanelState.STAFF_PROFILE);
        CommandResult expectedResult = new CommandResult(
                ListCommand.MESSAGE_SUCCESS_STAFF, false, false, true);
        assertCommandSuccess(new ListCommand(true), model, expectedResult, expectedModel);
    }

    @Test
    public void execute_staffListIsFilteredOnApplicantList_showsEverything() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);
        showApplicantAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setPanelState(PanelState.STAFF_LIST);
        model.setPanelState(PanelState.APPLICANT_LIST);
        CommandResult expectedResult = new CommandResult(
                ListCommand.MESSAGE_SUCCESS_STAFF, false, false, true);
        assertCommandSuccess(new ListCommand(true), model, expectedResult, expectedModel);
    }

    @Test
    public void execute_staffListIsFilteredOnApplicantProfile_showsEverything() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);
        showApplicantAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setPanelState(PanelState.STAFF_LIST);
        model.setPanelState(PanelState.APPLICANT_PROFILE);
        CommandResult expectedResult = new CommandResult(
                ListCommand.MESSAGE_SUCCESS_STAFF, false, false, true);
        assertCommandSuccess(new ListCommand(true), model, expectedResult, expectedModel);
    }

    @Test
    public void execute_applicantListIsNotFilteredOnApplicantList_showsSameList() {
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        model.setPanelState(PanelState.APPLICANT_LIST);
        CommandResult expectedResult = new CommandResult(
                ListCommand.MESSAGE_SUCCESS_APPLICANT, false, false, true);
        assertCommandSuccess(new ListCommand(false), model, expectedResult, expectedModel);
    }

    @Test
    public void execute_applicantListIsFilteredOnApplicantList_showsEverything() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        model.setPanelState(PanelState.APPLICANT_LIST);
        CommandResult expectedResult = new CommandResult(
                ListCommand.MESSAGE_SUCCESS_APPLICANT, false, false, true);
        assertCommandSuccess(new ListCommand(false), model, expectedResult, expectedModel);
    }

    @Test
    public void execute_applicantListIsFilteredOnApplicantProfile_showsEverything() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        model.setPanelState(PanelState.APPLICANT_PROFILE);
        CommandResult expectedResult = new CommandResult(
                ListCommand.MESSAGE_SUCCESS_APPLICANT, false, false, true);
        assertCommandSuccess(new ListCommand(false), model, expectedResult, expectedModel);
    }

    @Test
    public void execute_applicantListIsFilteredOnStaffList_showsEverything() {
        showStaffAtIndex(model, INDEX_FIRST_PERSON);
        showStaffAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        model.setPanelState(PanelState.STAFF_LIST);
        CommandResult expectedResult = new CommandResult(
                ListCommand.MESSAGE_SUCCESS_APPLICANT, false, false, true);
        assertCommandSuccess(new ListCommand(false), model, expectedResult, expectedModel);
    }

    @Test
    public void execute_applicantListIsFilteredOnStaffProfile_showsEverything() {
        showStaffAtIndex(model, INDEX_FIRST_PERSON);
        showStaffAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        model.setPanelState(PanelState.STAFF_PROFILE);
        CommandResult expectedResult = new CommandResult(
                ListCommand.MESSAGE_SUCCESS_APPLICANT, false, false, true);
        assertCommandSuccess(new ListCommand(false), model, expectedResult, expectedModel);
    }

}
