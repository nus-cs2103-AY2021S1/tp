package seedu.stock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.stock.logic.commands.CommandTestUtil.showStockAtSerialNumber;
import static seedu.stock.testutil.TypicalSerialNumberSets.getTypicalSerialNumberSetsBook;
import static seedu.stock.testutil.TypicalStocks.APPLE;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_FIRST_STOCK;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_SECOND_STOCK;
import static seedu.stock.testutil.TypicalStocks.UNKNOWN_SERIAL_NUMBER;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;
import static seedu.stock.testutil.TypicalStocks.serialNumberListAsString;
import static seedu.stock.testutil.TypicalStocks.stocksAsString;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.stock.commons.core.Messages;
import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.UserPrefs;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {
    private Model model = new ModelManager(getTypicalStockBook(), new UserPrefs(),
                                                    getTypicalSerialNumberSetsBook());

    @Test
    public void execute_validSerialNumbersUnfilteredList_success() {
        Set<Stock> stocksToDelete = new LinkedHashSet<>();
        model.getFilteredStockList().forEach(stock -> stocksToDelete.add(stock));

        Set<SerialNumber> serialNumbersGiven = new LinkedHashSet<>();
        List<Stock> stocksList = List.copyOf(stocksToDelete);

        stocksList.forEach(stock -> serialNumbersGiven.add(stock.getSerialNumber()));

        DeleteCommand deleteCommand = new DeleteCommand(serialNumbersGiven);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STOCK_SUCCESS,
                                                    stocksAsString(stocksList));

        ModelManager expectedModel = new ModelManager(model.getStockBook(), new UserPrefs(),
                                                                model.getSerialNumberSetsBook());

        stocksToDelete.forEach(stock -> expectedModel.deleteStock(stock));

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidSerialNumbersQuantity_throwsCommandException() throws CommandException {
        DeleteCommand deleteCommand = new DeleteCommand(new LinkedHashSet<>());

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NO_SERIAL_NUMBERS_GIVEN);
    }

    @Test
    public void execute_validSerialNumbersFilteredList_success() {
        showStockAtSerialNumber(model, SERIAL_NUMBER_FIRST_STOCK);

        //initialises for delete command usage.
        Set<SerialNumber> firstSerialNumberSet = new LinkedHashSet<>();
        firstSerialNumberSet.add(SERIAL_NUMBER_FIRST_STOCK);

        Stock stockToDelete = APPLE;
        List<Stock> firstStock = new ArrayList<>();
        firstStock.add(stockToDelete);

        DeleteCommand deleteCommand = new DeleteCommand(firstSerialNumberSet);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STOCK_SUCCESS,
                                                stocksAsString(firstStock));

        ModelManager expectedModel = new ModelManager(model.getStockBook(), new UserPrefs(),
                model.getSerialNumberSetsBook());

        expectedModel.deleteStock(stockToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidSerialNumberFilteredList_throwsCommandException() {
        showStockAtSerialNumber(model, SERIAL_NUMBER_FIRST_STOCK);

        // ensures that SERIAL_NUMBER_SECOND_STOCK is still in the stock book list.
        assertTrue(model.getStockBook().getStockList().stream()
                .anyMatch(stock -> stock.getSerialNumber().equals(SERIAL_NUMBER_SECOND_STOCK)));

        Set<SerialNumber> secondSerialNumberSet = new LinkedHashSet<>();
        secondSerialNumberSet.add(UNKNOWN_SERIAL_NUMBER);

        DeleteCommand deleteCommand = new DeleteCommand(secondSerialNumberSet);

        String expectedMessage = String.format(Messages.MESSAGE_SERIAL_NUMBER_NOT_FOUND,
                serialNumberListAsString(List.copyOf(secondSerialNumberSet)));
        model.updateFilteredStockList(Model.PREDICATE_SHOW_ALL_STOCKS);

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        //initialises sets for delete command usage.
        Set<SerialNumber> firstSerialNumberSet = new LinkedHashSet<>();
        firstSerialNumberSet.add(SERIAL_NUMBER_FIRST_STOCK);
        Set<SerialNumber> secondSerialNumberSet = new LinkedHashSet<>();
        secondSerialNumberSet.add(SERIAL_NUMBER_SECOND_STOCK);

        DeleteCommand deleteFirstCommand = new DeleteCommand(firstSerialNumberSet);
        DeleteCommand deleteSecondCommand = new DeleteCommand(secondSerialNumberSet);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(firstSerialNumberSet);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different serial numbers -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no stocks.
     */
    private void showNoStock(Model model) {
        model.updateFilteredStockList(p -> false);

        assertTrue(model.getFilteredStockList().isEmpty());
    }
}
