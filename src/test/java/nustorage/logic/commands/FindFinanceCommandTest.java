package nustorage.logic.commands;

import static nustorage.logic.commands.CommandTestUtil.DATE_B;
import static nustorage.logic.commands.CommandTestUtil.DATE_C;
import static nustorage.logic.commands.CommandTestUtil.ID_A;
import static nustorage.logic.commands.CommandTestUtil.ID_B;
import static nustorage.logic.commands.CommandTestUtil.assertCommandSuccess;
import static nustorage.testutil.TypicalFinanceRecords.RECORD_A;
import static nustorage.testutil.TypicalFinanceRecords.RECORD_B;
import static nustorage.testutil.TypicalFinanceRecords.RECORD_C;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import nustorage.commons.core.Messages;
import nustorage.model.Model;
import nustorage.model.ModelManager;
import nustorage.model.UserPrefs;
import nustorage.testutil.TypicalFinanceRecords;
import nustorage.testutil.TypicalInventoryRecords;

public class FindFinanceCommandTest {

    private Model model = new ModelManager(TypicalFinanceRecords.getTypicalFinanceAccount(),
            TypicalInventoryRecords.getTypicalInventory(),
            new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalFinanceRecords.getTypicalFinanceAccount(),
            TypicalInventoryRecords.getTypicalInventory(),
            new UserPrefs());

    @Test
    public void equals() {

        FindFinanceCommand findFirstCommand = new FindFinanceCommand().setIdMatch(String.valueOf(ID_A));
        FindFinanceCommand findSecondCommand = new FindFinanceCommand().setIdMatch(String.valueOf(ID_B));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindFinanceCommand findFirstCommandCopy = new FindFinanceCommand().setIdMatch(String.valueOf(ID_A));
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different finance record -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noRecordFound() {
        String expectedMessage = String.format(Messages.MESSAGE_FINANCE_LISTED_OVERVIEW, 0);

        FindFinanceCommand findFinanceCommand = new FindFinanceCommand().setIdMatch("ID are int strings");
        expectedModel.updateFilteredFinanceList(findFinanceCommand.getPredicate());
        assertCommandSuccess(findFinanceCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFinanceList());
    }

    @Test
    public void execute_findId_oneRecordFound() {
        String expectedMessage = String.format(Messages.MESSAGE_FINANCE_LISTED_OVERVIEW, 1);
        FindFinanceCommand findFinanceCommand = new FindFinanceCommand().setIdMatch(String.valueOf(ID_A));
        expectedModel.updateFilteredFinanceList(findFinanceCommand.getPredicate());
        assertCommandSuccess(findFinanceCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(RECORD_A), model.getFilteredFinanceList());
    }

    @Test
    public void execute_findDate_someRecordsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_FINANCE_LISTED_OVERVIEW, 2);
        FindFinanceCommand findFinanceCommand = new FindFinanceCommand()
                .setAfterDatetime(DATE_C)
                .setBeforeDatetime(DATE_B);
        expectedModel.updateFilteredFinanceList(findFinanceCommand.getPredicate());
        assertCommandSuccess(findFinanceCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(RECORD_B, RECORD_C), model.getFilteredFinanceList());
    }
}
