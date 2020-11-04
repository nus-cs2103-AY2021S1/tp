/*
package com.eva.logic.commands;

import static com.eva.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.eva.testutil.TypicalPersons.getTypicalApplicantDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalStaffDatabase;

import com.eva.commons.core.PanelState;
import org.junit.jupiter.api.Test;

import com.eva.logic.parser.Prefix;
import com.eva.model.EvaDatabase;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.UserPrefs;
public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        model.setPanelState(PanelState.APPLICANT_LIST);
        assertCommandSuccess(new ClearCommand(new Prefix("a-")), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBookApplicants_success() {
        Model model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        expectedModel.setApplicantDatabase(new EvaDatabase<>());
        assertCommandSuccess(new ClearCommand(new Prefix("a-")), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
*/
