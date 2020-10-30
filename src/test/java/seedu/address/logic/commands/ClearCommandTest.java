package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalClientList;
import static seedu.address.testutil.TypicalPolicies.getTypicalPolicyList;

import org.junit.jupiter.api.Test;

import seedu.address.model.ClientList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyClientList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyClientList_success() {
        Model model = new ModelManager(getTypicalClientList(), new UserPrefs(), getTypicalPolicyList());
        Model expectedModel = new ModelManager(getTypicalClientList(), new UserPrefs(), getTypicalPolicyList());
        expectedModel.setClientList(new ClientList());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
