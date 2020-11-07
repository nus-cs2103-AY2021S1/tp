package nustorage.logic.commands;

import static nustorage.commons.core.Messages.MESSAGE_INVENTORY_RECORDS_LISTED_OVERVIEW;
import static nustorage.logic.commands.CommandTestUtil.assertCommandSuccess;
import static nustorage.testutil.TypicalFinanceRecords.getTypicalFinanceAccount;
import static nustorage.testutil.TypicalInventoryRecords.INVENTORY_RECORD_A;
import static nustorage.testutil.TypicalInventoryRecords.INVENTORY_RECORD_B;
import static nustorage.testutil.TypicalInventoryRecords.INVENTORY_RECORD_C;
import static nustorage.testutil.TypicalInventoryRecords.getTypicalInventory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import nustorage.model.Model;
import nustorage.model.ModelManager;
import nustorage.model.UserPrefs;
import nustorage.model.record.InventoryRecordNameContainsKeywordsPredicate;

public class FindInventoryRecordCommandTest {
    private Model model = new ModelManager(getTypicalFinanceAccount(), getTypicalInventory(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFinanceAccount(), getTypicalInventory(), new UserPrefs());

    @Test
    public void equals() {
        InventoryRecordNameContainsKeywordsPredicate firstPredicate =
                new InventoryRecordNameContainsKeywordsPredicate(Collections.singletonList("first"));
        InventoryRecordNameContainsKeywordsPredicate secondPredicate =
                new InventoryRecordNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindInventoryRecordCommand findFirstCommand = new FindInventoryRecordCommand(firstPredicate);
        FindInventoryRecordCommand findSecondCommand = new FindInventoryRecordCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindInventoryRecordCommand findFirstCommandCopy = new FindInventoryRecordCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different inventory record -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noInventoryRecordFound() {
        String expectedMessage = String.format(MESSAGE_INVENTORY_RECORDS_LISTED_OVERVIEW, 0);
        InventoryRecordNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindInventoryRecordCommand command = new FindInventoryRecordCommand(predicate);
        expectedModel.updateFilteredInventoryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredInventory());
    }

    @Test
    public void execute_multipleKeywords_multipleInventoryRecordsFound() {
        String expectedMessage = String.format(MESSAGE_INVENTORY_RECORDS_LISTED_OVERVIEW, 3);
        InventoryRecordNameContainsKeywordsPredicate predicate = preparePredicate(
                "ITEM_NAME_1 ITEM_NAME_2 ITEM_NAME_3");
        FindInventoryRecordCommand command = new FindInventoryRecordCommand(predicate);
        expectedModel.updateFilteredInventoryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(INVENTORY_RECORD_A, INVENTORY_RECORD_B, INVENTORY_RECORD_C),
                model.getFilteredInventory());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private InventoryRecordNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new InventoryRecordNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
