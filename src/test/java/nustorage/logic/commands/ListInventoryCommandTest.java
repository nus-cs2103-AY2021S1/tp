package nustorage.logic.commands;

import static nustorage.logic.commands.CommandTestUtil.assertCommandSuccess;
import static nustorage.testutil.TypicalFinanceRecords.getTypicalFinanceAccount;
import static nustorage.testutil.TypicalInventoryRecords.getTypicalInventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nustorage.model.Model;
import nustorage.model.ModelManager;
import nustorage.model.UserPrefs;

public class ListInventoryCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFinanceAccount(), getTypicalInventory(), new UserPrefs());
        expectedModel = new ModelManager(model.getFinanceAccount(), model.getInventory(), new UserPrefs());
    }

    @Test
    public void execute_inventoryListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListInventoryCommand(), model,
                ListInventoryCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
