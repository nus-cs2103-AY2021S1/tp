package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalItems.getTypicalInventoryBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.inventorymodel.InventoryBook;
import seedu.address.model.inventorymodel.InventoryModel;
import seedu.address.model.inventorymodel.InventoryModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyInventoryBook_success() {
        InventoryModel inventoryModel = new InventoryModelManager();
        InventoryModel expectedInventoryModel = new InventoryModelManager();

        assertCommandSuccess(new ClearCommand(), inventoryModel, ClearCommand.MESSAGE_SUCCESS, expectedInventoryModel);
    }

    @Test
    public void execute_nonEmptyInventoryBook_success() {
        InventoryModel inventoryModel = new InventoryModelManager(getTypicalInventoryBook(), new UserPrefs());
        InventoryModel expectedInventoryModel = new InventoryModelManager(getTypicalInventoryBook(), new UserPrefs());
        expectedInventoryModel.setInventoryBook(new InventoryBook());

        assertCommandSuccess(new ClearCommand(), inventoryModel, ClearCommand.MESSAGE_SUCCESS, expectedInventoryModel);
    }

}
