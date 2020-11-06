package com.eva.logic.commands;

import static com.eva.logic.commands.ClearCommand.MESSAGE_WRONG_PANEL;
import static com.eva.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.eva.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static com.eva.logic.parser.CliSyntax.PREFIX_STAFF;
import static com.eva.testutil.Assert.assertThrows;
import static com.eva.testutil.TypicalPersons.getTypicalApplicantDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalStaffDatabase;

import org.junit.jupiter.api.Test;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.EvaDatabase;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.UserPrefs;
import com.eva.model.person.staff.Staff;


public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        model.setPanelState(PanelState.APPLICANT_LIST);
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);

        assertCommandSuccess(new ClearCommand(PREFIX_APPLICANT), model,
                String.format(ClearCommand.MESSAGE_SUCCESS, "applicants"), expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBookApplicants_success() {
        Model model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        model.setPanelState(PanelState.APPLICANT_LIST);
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        expectedModel.setApplicantDatabase(new EvaDatabase<>());
        assertCommandSuccess(new ClearCommand(PREFIX_APPLICANT), model,
                String.format(ClearCommand.MESSAGE_SUCCESS, "applicants"), expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBookStaff_success() {
        Model model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        model.setPanelState(PanelState.STAFF_LIST);
        expectedModel.setPanelState(PanelState.STAFF_LIST);
        expectedModel.setStaffDatabase(new EvaDatabase<Staff>());
        assertCommandSuccess(new ClearCommand(PREFIX_STAFF), model,
                String.format(ClearCommand.MESSAGE_SUCCESS, "staff"), expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBookApplicant_failure() {
        Model model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        model.setPanelState(PanelState.STAFF_LIST);
        assertThrows(CommandException.class,
            String.format(Messages.MESSAGE_INVALID_COMMAND_AT_PANEL,
            String.format(MESSAGE_WRONG_PANEL, "applicants", PREFIX_APPLICANT)), () ->
                        new ClearCommand(PREFIX_APPLICANT).execute(model));
    }

    @Test
    public void execute_nonEmptyAddressBookStaff_failure() {
        Model model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        model.setPanelState(PanelState.APPLICANT_LIST);
        assertThrows(CommandException.class,
            String.format(Messages.MESSAGE_INVALID_COMMAND_AT_PANEL,
            String.format(MESSAGE_WRONG_PANEL, "staff", PREFIX_STAFF)), () ->
                        new ClearCommand(PREFIX_STAFF).execute(model));
    }
}
