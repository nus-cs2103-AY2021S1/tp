package nustorage.logic.commands;

import static nustorage.logic.commands.CommandTestUtil.AMOUNT_B;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_B;
import static nustorage.logic.commands.CommandTestUtil.DESC_FINANCE_A;
import static nustorage.logic.commands.CommandTestUtil.DESC_FINANCE_B;
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
import nustorage.testutil.EditFinanceDescriptorBuilder;
import nustorage.testutil.FinanceRecordBuilder;
import nustorage.testutil.TypicalFinanceRecords;
import nustorage.testutil.TypicalInventoryRecords;

public class EditFinanceCommandTest {

    private Model model = new ModelManager(TypicalFinanceRecords.getTypicalFinanceAccount(),
            TypicalInventoryRecords.getTypicalInventory(),
            new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        FinanceRecord editedFinanceRecord = new FinanceRecordBuilder()
                .withId(model.getFilteredFinanceList().get(0).getID()).build();
        EditFinanceCommand.EditFinanceDescriptor descriptor =
                new EditFinanceDescriptorBuilder(editedFinanceRecord).build();
        EditFinanceCommand editCommand = new EditFinanceCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditFinanceCommand.MESSAGE_EDIT_FINANCE_SUCCESS, editedFinanceRecord);

        Model expectedModel = new ModelManager(model.getFinanceAccount(),
                TypicalInventoryRecords.getTypicalInventory(),
                new UserPrefs());

        expectedModel.setFinanceRecord(model.getFilteredFinanceList().get(0), editedFinanceRecord);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastRecord = Index.fromOneBased(model.getFilteredFinanceList().size());
        FinanceRecord lastFinanceRecord = model.getFilteredFinanceList().get(indexLastRecord.getZeroBased());

        FinanceRecordBuilder financeRecordInList = new FinanceRecordBuilder(lastFinanceRecord);
        FinanceRecord editedFinanceRecord = financeRecordInList
                .withAmount(AMOUNT_B)
                .withDateTime(DATE_TIME_B).build();

        EditFinanceCommand.EditFinanceDescriptor descriptor = new EditFinanceDescriptorBuilder()
                .withAmount(AMOUNT_B)
                .withDateTime(DATE_TIME_B).build();
        EditFinanceCommand editFinanceCommand = new EditFinanceCommand(indexLastRecord, descriptor);

        String expectedMessage = String.format(EditFinanceCommand.MESSAGE_EDIT_FINANCE_SUCCESS, editedFinanceRecord);

        Model expectedModel = new ModelManager(model.getFinanceAccount(),
                TypicalInventoryRecords.getTypicalInventory(),
                new UserPrefs());
        expectedModel.setFinanceRecord(lastFinanceRecord, editedFinanceRecord);

        assertCommandSuccess(editFinanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_failure() {
        EditFinanceCommand editFinanceCommand =
                new EditFinanceCommand(INDEX_FIRST,
                        new EditFinanceCommand.EditFinanceDescriptor());
        assertCommandFailure(editFinanceCommand, model, EditFinanceCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void execute_filteredList_success() {
        showFinanceRecordAtIndex(model, INDEX_FIRST);

        FinanceRecord financeRecordInList = model.getFilteredFinanceList().get(INDEX_FIRST.getZeroBased());
        FinanceRecord editedFinanceRecord = new FinanceRecordBuilder(financeRecordInList).withAmount(AMOUNT_B).build();
        EditFinanceCommand editFinanceCommand = new EditFinanceCommand(INDEX_FIRST,
                new EditFinanceDescriptorBuilder().withAmount(AMOUNT_B).build());

        String expectedMessage = String.format(EditFinanceCommand.MESSAGE_EDIT_FINANCE_SUCCESS, editedFinanceRecord);

        Model expectedModel = new ModelManager(model.getFinanceAccount(),
                TypicalInventoryRecords.getTypicalInventory(),
                new UserPrefs());
        expectedModel.setFinanceRecord(model.getFilteredFinanceList().get(0), editedFinanceRecord);

        assertCommandSuccess(editFinanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidFinanceRecordIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFinanceList().size() + 1);
        EditFinanceCommand.EditFinanceDescriptor descriptor =
                new EditFinanceDescriptorBuilder().withAmount(AMOUNT_B).build();
        EditFinanceCommand editFinanceCommand = new EditFinanceCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editFinanceCommand, model, Messages.MESSAGE_INVALID_FINANCE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of finance record list
     */
    @Test
    public void execute_invalidFinanceRecordIndexFilteredList_failure() {
        showFinanceRecordAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of finance records list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFinanceAccount().getFinanceList().size());

        EditFinanceCommand editFinanceCommand = new EditFinanceCommand(outOfBoundIndex,
                new EditFinanceDescriptorBuilder().withAmount(AMOUNT_B).build());

        assertCommandFailure(editFinanceCommand, model, Messages.MESSAGE_INVALID_FINANCE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditFinanceCommand standardCommand = new EditFinanceCommand(INDEX_FIRST, DESC_FINANCE_A);

        // same values -> returns true
        EditFinanceCommand.EditFinanceDescriptor copyDescriptor =
                new EditFinanceCommand.EditFinanceDescriptor(DESC_FINANCE_A);
        EditFinanceCommand commandWithSameValues = new EditFinanceCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditFinanceCommand(INDEX_SECOND, DESC_FINANCE_A)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditFinanceCommand(INDEX_FIRST, DESC_FINANCE_B)));
    }
}
