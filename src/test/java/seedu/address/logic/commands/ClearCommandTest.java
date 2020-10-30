package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssignments.getTypicalProductiveNus;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ProductiveNus;
import seedu.address.model.UserPrefs;

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
