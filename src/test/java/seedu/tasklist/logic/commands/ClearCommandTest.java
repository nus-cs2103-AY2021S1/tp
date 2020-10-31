package seedu.tasklist.logic.commands;

import static seedu.tasklist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tasklist.testutil.TypicalAssignments.getTypicalProductiveNus;

import org.junit.jupiter.api.Test;

import seedu.tasklist.model.Model;
import seedu.tasklist.model.ModelManager;
import seedu.tasklist.model.ProductiveNus;
import seedu.tasklist.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyProductiveNus_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.setProductiveNus(new ProductiveNus());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyProductiveNus_success() {
        Model model = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);
        Model expectedModel = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);
        expectedModel.setProductiveNus(new ProductiveNus());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
