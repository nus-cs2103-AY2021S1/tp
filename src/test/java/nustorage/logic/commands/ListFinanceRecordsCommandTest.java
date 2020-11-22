
package nustorage.logic.commands;

import static nustorage.logic.commands.CommandTestUtil.assertCommandSuccess;
import static nustorage.testutil.TypicalFinanceRecords.getTypicalFinanceAccount;
import static nustorage.testutil.TypicalInventoryRecords.getTypicalInventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nustorage.model.Model;
import nustorage.model.ModelManager;
import nustorage.model.UserPrefs;

public class ListFinanceRecordsCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFinanceAccount(), getTypicalInventory(), new UserPrefs());
        expectedModel = new ModelManager(model.getFinanceAccount(), model.getInventory(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListFinanceRecordsCommand(), model,
                ListFinanceRecordsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
