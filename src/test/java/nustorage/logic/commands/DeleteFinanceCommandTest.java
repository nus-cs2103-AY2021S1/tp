package nustorage.logic.commands;

import static nustorage.logic.commands.CommandTestUtil.assertCommandFailure;
import static nustorage.logic.commands.CommandTestUtil.assertCommandSuccess;
import static nustorage.logic.commands.CommandTestUtil.showFinanceRecordAtIndex;
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
import nustorage.model.record.FinanceRecord;
import nustorage.testutil.TypicalFinanceRecords;
import nustorage.testutil.TypicalInventoryRecords;

public class DeleteFinanceCommandTest {

    private Model model = new ModelManager(TypicalFinanceRecords.getTypicalFinanceAccount(),
            TypicalInventoryRecords.getTypicalInventory(),
            new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        FinanceRecord financeToDelete = model.getFilteredFinanceList().get(INDEX_FIRST.getZeroBased());
        DeleteFinanceCommand deleteFinanceCommand = new DeleteFinanceCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteFinanceCommand.MESSAGE_SUCCESS, financeToDelete);

        ModelManager expectedModel = new ModelManager(model.getFinanceAccount(),
                TypicalInventoryRecords.getTypicalInventory(),
                new UserPrefs());
        expectedModel.deleteFinanceRecord(financeToDelete);

        assertCommandSuccess(deleteFinanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFinanceList().size() + 1);
        DeleteFinanceCommand deleteFinanceCommand = new DeleteFinanceCommand(outOfBoundIndex);

        assertCommandFailure(deleteFinanceCommand, model, Messages.MESSAGE_INVALID_FINANCE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showFinanceRecordAtIndex(model, INDEX_FIRST);

        FinanceRecord financeToDelete = model.getFilteredFinanceList().get(INDEX_FIRST.getZeroBased());
        DeleteFinanceCommand deleteFinanceCommand = new DeleteFinanceCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteFinanceCommand.MESSAGE_SUCCESS, financeToDelete);

        Model expectedModel = new ModelManager(model.getFinanceAccount(),
                TypicalInventoryRecords.getTypicalInventory(),
                new UserPrefs());
        expectedModel.deleteFinanceRecord(financeToDelete);
        showNoFinanceRecord(expectedModel);

        assertCommandSuccess(deleteFinanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFinanceRecordAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of finance records list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFinanceAccount().getFinanceList().size());

        DeleteFinanceCommand deleteFinanceCommand = new DeleteFinanceCommand(outOfBoundIndex);

        assertCommandFailure(deleteFinanceCommand, model, Messages.MESSAGE_INVALID_FINANCE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteFinanceCommand deleteFirstCommand = new DeleteFinanceCommand(INDEX_FIRST);
        DeleteFinanceCommand deleteSecondCommand = new DeleteFinanceCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteFinanceCommand deleteFirstCommandCopy = new DeleteFinanceCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different finance record -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no finance records.
     */
    private void showNoFinanceRecord(Model model) {
        model.updateFilteredFinanceList(p -> false);

        assertTrue(model.getFilteredFinanceList().isEmpty());
    }
}
