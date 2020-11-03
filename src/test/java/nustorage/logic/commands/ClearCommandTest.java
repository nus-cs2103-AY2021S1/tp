package nustorage.logic.commands;

import static nustorage.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import nustorage.model.FinanceAccount;
import nustorage.model.Inventory;
import nustorage.model.Model;
import nustorage.model.ModelManager;
import nustorage.model.UserPrefs;
import nustorage.testutil.TypicalFinanceRecords;
import nustorage.testutil.TypicalInventoryRecords;

public class ClearCommandTest {

    @Test
    public void execute_emptyNuStorage_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyNuStorage_success() {
        Model model = new ModelManager(TypicalFinanceRecords.getTypicalFinanceAccount(),
                TypicalInventoryRecords.getTypicalInventory(),
                new UserPrefs());
        Model expectedModel = new ModelManager(TypicalFinanceRecords.getTypicalFinanceAccount(),
                TypicalInventoryRecords.getTypicalInventory(),
                new UserPrefs());
        expectedModel.setFinanceAccount(new FinanceAccount());
        expectedModel.setInventory(new Inventory());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
