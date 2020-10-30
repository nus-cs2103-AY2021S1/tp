package com.eva.logic.commands;

import static com.eva.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.eva.testutil.TypicalPersons.getTypicalApplicantDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalStaffDatabase;

import org.junit.jupiter.api.Test;

import com.eva.model.EvaDatabase;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        expectedModel.setPersonDatabase(new EvaDatabase<>());
        expectedModel.setApplicantDatabase(new EvaDatabase<>());
        expectedModel.setStaffDatabase(new EvaDatabase<>());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
