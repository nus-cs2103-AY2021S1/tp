package seedu.stock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.stock.logic.commands.SortCommand.MESSAGE_SORT_STOCK_SUCCESS;
import static seedu.stock.testutil.TypicalStocks.getTypicalSerialNumberSetsBook;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;
import static seedu.stock.testutil.TypicalStocks.getTypicalStocks;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.stock.commons.util.SortUtil;
import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.SerialNumberSetsBook;
import seedu.stock.model.StockBook;
import seedu.stock.model.UserPrefs;
import seedu.stock.model.stock.Stock;

public class SortCommandTest {

    private SerialNumberSetsBook serialNumbers = getTypicalSerialNumberSetsBook();
    private Model model = new ModelManager(getTypicalStockBook(), new UserPrefs(), serialNumbers);

    @Test
    public void execute_ascendingOrder_success() {
        SortCommand sortCommand = new SortCommand(SortUtil.Field.SERIALNUMBER, false);
        List<Stock> sortedStocks = getTypicalStocks();
        Comparator<Stock> serialNumberComparator = SortUtil.generateComparator(SortUtil.Field.SERIALNUMBER);
        sortedStocks.sort(serialNumberComparator);
        StockBook sortedStockBook = new StockBook();
        sortedStockBook.setStocks(sortedStocks);

        String expectedMessage =
                String.format(MESSAGE_SORT_STOCK_SUCCESS, SortUtil.getFieldDescription(SortUtil.Field.SERIALNUMBER));
        Model expectedModel = new ModelManager(sortedStockBook, new UserPrefs(),
                new SerialNumberSetsBook(model.getSerialNumberSetsBook()));

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_descendingOrder_success() {
        SortCommand sortCommand = new SortCommand(SortUtil.Field.SERIALNUMBER, true);
        List<Stock> sortedStocks = getTypicalStocks();
        Comparator<Stock> serialNumberComparator = SortUtil.generateReverseComparator(SortUtil.Field.SERIALNUMBER);
        sortedStocks.sort(serialNumberComparator);
        StockBook sortedStockBook = new StockBook();
        sortedStockBook.setStocks(sortedStocks);

        String expectedMessage =
                String.format(MESSAGE_SORT_STOCK_SUCCESS, SortUtil.getFieldDescription(SortUtil.Field.SERIALNUMBER));
        Model expectedModel = new ModelManager(sortedStockBook, new UserPrefs(),
                new SerialNumberSetsBook(model.getSerialNumberSetsBook()));

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        SortUtil.Field fieldToSort = SortUtil.Field.SERIALNUMBER;
        boolean isReversed = false;
        final SortCommand standardCommand = new SortCommand(fieldToSort, isReversed);

        // same values -> returns true
        SortUtil.Field copyFieldToSort = SortUtil.Field.SERIALNUMBER;
        boolean copyIsReversed = true;
        SortCommand commandWithSameValues = new SortCommand(fieldToSort, isReversed);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ExitCommand()));

        // different values -> returns false
        SortCommand differentCommand = new SortCommand(fieldToSort, true);
        assertFalse(standardCommand.equals(differentCommand));
    }
}
