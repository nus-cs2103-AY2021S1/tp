package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ClearCommandTest {

    @Test
    public void execute_emptyInventoryBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    //    @Test
    //    public void execute_nonEmptyInventoryBook_success() {
    //        Model model = new ModelManager(getTypicalInventoryBook(), new UserPrefs());
    //        Model expectedModel = new ModelManager(getTypicalInventoryBook(), new UserPrefs());
    //        expectedModel.setInventoryBook(new InventoryBook());
    //
    //        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    //    }

}
