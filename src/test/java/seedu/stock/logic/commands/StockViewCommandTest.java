package seedu.stock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.logic.commands.CommandTestUtil.assertCommandFailureForNote;
import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.stock.logic.commands.CommandTestUtil.isSerialNumberInStockBook;
import static seedu.stock.logic.commands.CommandTestUtil.showStockAtSerialNumber;
import static seedu.stock.testutil.Assert.assertThrows;
import static seedu.stock.testutil.TypicalSerialNumberSets.getTypicalSerialNumberSetsBook;
import static seedu.stock.testutil.TypicalStocks.INDEX_FIRST_STOCK;
import static seedu.stock.testutil.TypicalStocks.INDEX_SECOND_STOCK;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_FIRST_STOCK;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_SECOND_STOCK;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_THIRD_STOCK;
import static seedu.stock.testutil.TypicalStocks.UNKNOWN_SERIAL_NUMBER;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;

import org.junit.jupiter.api.Test;

import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.SerialNumberSetsBook;
import seedu.stock.model.StockBook;
import seedu.stock.model.UserPrefs;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;

/**
 * Contains integration tests (interaction with the Model) for {@code StockViewCommand}.
 */
public class StockViewCommandTest {

    private SerialNumberSetsBook serialNumbers = getTypicalSerialNumberSetsBook();
    private Model model = new ModelManager(getTypicalStockBook(), new UserPrefs(), serialNumbers);

    @Test
    public void constructor_nullSerialNumber_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StockViewCommand(null));
    }

    @Test
    public void execute_stockViewUnfilteredList_success() {

        Stock firstStock = model.getFilteredStockList().get(INDEX_FIRST_STOCK.getZeroBased());

        StockViewCommand stockViewCommand = new StockViewCommand(SERIAL_NUMBER_FIRST_STOCK);

        String expectedMessage = String.format(StockViewCommand.MESSAGE_NOTE_DISPLAY_SUCCESS, firstStock);

        Model expectedModel = new ModelManager(getTypicalStockBook(), new UserPrefs(),
                getTypicalSerialNumberSetsBook());

        assertCommandSuccess(stockViewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_stockViewFilteredList_success() {

        showStockAtSerialNumber(model, SERIAL_NUMBER_FIRST_STOCK);

        Stock firstStock = model.getFilteredStockList().get(INDEX_FIRST_STOCK.getZeroBased());

        String expectedMessage = String.format(StockViewCommand.MESSAGE_NOTE_DISPLAY_SUCCESS, firstStock);
        Model expectedModel = new ModelManager(new StockBook(model.getStockBook()), new UserPrefs(),
                new SerialNumberSetsBook(model.getSerialNumberSetsBook()));

        StockViewCommand stockViewCommand = new StockViewCommand(SERIAL_NUMBER_FIRST_STOCK);

        assertCommandSuccess(stockViewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_serialNumberNotFoundUnfilteredList_failure() {

        StockViewCommand stockViewCommand = new StockViewCommand(UNKNOWN_SERIAL_NUMBER);

        assertCommandFailureForNote(stockViewCommand, model, StockViewCommand.MESSAGE_SERIAL_NUMBER_NOT_FOUND);
    }

    /**
     * Edit filtered list where only shows the first stock of the stock book.
     */
    @Test
    public void execute_validSerialNumberNotInFilteredList_success() {

        showStockAtSerialNumber(model, SERIAL_NUMBER_FIRST_STOCK);

        SerialNumber serialNumberNotInFilteredList = SERIAL_NUMBER_SECOND_STOCK;
        // ensures that serial number not in filtered list is still in stock book
        assertTrue(isSerialNumberInStockBook(model, serialNumberNotInFilteredList));

        Stock secondStock = model.getStockBook().getStockList().get(INDEX_SECOND_STOCK.getZeroBased());

        String expectedMessage = String.format(StockViewCommand.MESSAGE_NOTE_DISPLAY_SUCCESS,
                secondStock);

        Model expectedModel = new ModelManager(new StockBook(model.getStockBook()), new UserPrefs(),
                new SerialNumberSetsBook(model.getSerialNumberSetsBook()));

        StockViewCommand stockViewCommand = new StockViewCommand(serialNumberNotInFilteredList);

        assertCommandSuccess(stockViewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final StockViewCommand standardCommand = new StockViewCommand(SERIAL_NUMBER_FIRST_STOCK);

        // same values -> returns true
        StockViewCommand commandWithSameValues = new StockViewCommand(SERIAL_NUMBER_FIRST_STOCK);

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different serial number -> returns false
        assertFalse(standardCommand.equals(new StockViewCommand(SERIAL_NUMBER_THIRD_STOCK)));

    }

}
