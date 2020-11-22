package nustorage.logic.commands;

import static nustorage.logic.commands.CommandTestUtil.assertCommandFailure;
import static nustorage.logic.commands.CommandTestUtil.showInventoryRecordAtIndex;
import static nustorage.testutil.TypicalIndexes.INDEX_FIRST;
import static nustorage.testutil.TypicalIndexes.INDEX_SECOND;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nustorage.commons.core.Messages;
import nustorage.commons.core.index.Index;
import nustorage.model.Model;
import nustorage.model.ModelManager;
import nustorage.model.UserPrefs;
import nustorage.testutil.TypicalFinanceRecords;
import nustorage.testutil.TypicalInventoryRecords;

public class DeleteInventoryRecordCommandTest {

    private Model model = new ModelManager(TypicalFinanceRecords.getTypicalFinanceAccount(),
            TypicalInventoryRecords.getTypicalInventory(),
            new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInventory().size() + 1);
        DeleteInventoryRecordCommand deleteInventoryCommand = new DeleteInventoryRecordCommand(outOfBoundIndex);

        assertCommandFailure(deleteInventoryCommand, model, Messages.MESSAGE_INVALID_INVENTORY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInventoryRecordAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of finance records list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInventory().getInventoryRecordList().size());

        DeleteInventoryRecordCommand deleteInventoryCommand = new DeleteInventoryRecordCommand(outOfBoundIndex);

        assertCommandFailure(deleteInventoryCommand, model, Messages.MESSAGE_INVALID_INVENTORY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteInventoryRecordCommand deleteFirstCommand = new DeleteInventoryRecordCommand(INDEX_FIRST);
        DeleteInventoryRecordCommand deleteSecondCommand = new DeleteInventoryRecordCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteInventoryRecordCommand deleteFirstCommandCopy = new DeleteInventoryRecordCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different finance record -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no inventory records.
     */
    private void showNoInventoryRecord(Model model) {
        model.updateFilteredInventoryList(p -> false);

        assertTrue(model.getFilteredInventory().isEmpty());
    }
}
