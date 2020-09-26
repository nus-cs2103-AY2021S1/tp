package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalCliniCal;

import org.junit.jupiter.api.Test;

import seedu.address.model.CliniCal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyCliniCal_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyCliniCal_success() {
        Model model = new ModelManager(getTypicalCliniCal(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalCliniCal(), new UserPrefs());
        expectedModel.setCliniCal(new CliniCal());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
