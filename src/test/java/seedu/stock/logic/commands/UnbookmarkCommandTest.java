package seedu.stock.logic.commands;

import static seedu.stock.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.stock.testutil.TypicalSerialNumberSets.getTypicalSerialNumberSetsBook;
import static seedu.stock.testutil.TypicalStocks.INDEX_FIRST_STOCK;
import static seedu.stock.testutil.TypicalStocks.INDEX_FOURTH_STOCK;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_FIRST_STOCK;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_FOURTH_STOCK;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.stock.commons.util.SortUtil;
import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.UserPrefs;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;
import seedu.stock.testutil.StockBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class UnbookmarkCommandTest {
    private Model model = new ModelManager(getTypicalStockBook(), new UserPrefs(),
            getTypicalSerialNumberSetsBook());


    @Test
    public void execute_unbookmarkUnfilteredList_success() {
        Set<SerialNumber> serialNumbersToBookmark = new LinkedHashSet<>();
        List<Stock> stocksToUnbookmark = new ArrayList<>();

        Stock firstStock = model.getFilteredStockList().get(INDEX_FIRST_STOCK.getZeroBased());
        Stock firstStockBookmarked = new StockBuilder(firstStock).copyOfStockBuilder().withBookmark(false).build();

        serialNumbersToBookmark.add(SERIAL_NUMBER_FIRST_STOCK);
        stocksToUnbookmark.add(firstStock);

        UnbookmarkCommand unbookmarkCommand = new UnbookmarkCommand(serialNumbersToBookmark);

        String expectedMessage = String.format(UnbookmarkCommand.MESSAGE_UNBOOKMARK_STOCK_SUCCESS,
                stocksAsString(stocksToUnbookmark));

        Model expectedModel = new ModelManager(getTypicalStockBook(), new UserPrefs(),
                getTypicalSerialNumberSetsBook());

        expectedModel.setStock(firstStock, firstStockBookmarked);

        expectedModel.sortFilteredStockList(SortUtil.generateGeneralComparator());

        assertCommandSuccess(unbookmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unbookmarkNotBookmarked_failure() {
        Set<SerialNumber> serialNumbersToBookmark = new LinkedHashSet<>();
        List<Stock> stocksToUnbookmark = new ArrayList<>();

        Stock secondStock = model.getFilteredStockList().get(INDEX_FOURTH_STOCK.getZeroBased());

        serialNumbersToBookmark.add(SERIAL_NUMBER_FOURTH_STOCK);
        stocksToUnbookmark.add(secondStock);

        UnbookmarkCommand unbookmarkCommand = new UnbookmarkCommand(serialNumbersToBookmark);

        String expectedMessage = String.format(UnbookmarkCommand.MESSAGE_NOT_BOOKMARKED,
                stocksAsString(stocksToUnbookmark));

        assertCommandFailure(unbookmarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_unbookmarkInvalidSerialNumber_failure() {
        Set<SerialNumber> serialNumbersToUnbookmark = new LinkedHashSet<>();
        List<Stock> stocksToUnbookmark = new ArrayList<>();
        SerialNumber invalidSerialNumber = new SerialNumber("ABC1");

        Stock firstStock = model.getFilteredStockList().get(INDEX_FIRST_STOCK.getZeroBased());
        Stock invalidSerialNumberStock = new StockBuilder(firstStock).copyOfStockBuilder()
                .withSerialNumber("ABC1").build();

        serialNumbersToUnbookmark.add(invalidSerialNumber);
        stocksToUnbookmark.add(invalidSerialNumberStock);

        UnbookmarkCommand unbookmarkCommand = new UnbookmarkCommand(serialNumbersToUnbookmark);

        String expectedMessage = String.format(UnbookmarkCommand.MESSAGE_SERIAL_NUMBER_NOT_FOUND,
                arrayAsString(stocksToUnbookmark));

        assertCommandFailure(unbookmarkCommand, model, expectedMessage);
    }


    /**
     * Displays the list of stocks in a clearer view, with each subsequent stock moved to the next line.
     *
     * @param stockList The list of stocks to be converted to String.
     * @return The String listing all stocks in the list.
     */
    private String stocksAsString(List<Stock> stockList) {
        String stocksAsString = "";
        for (int i = 0; i < stockList.size(); i++) {
            stocksAsString += "\n" + stockList.get(i).toString();
        }
        return stockList.size() == 0 ? "No stocks updated" : stocksAsString;
    }

    /**
     * Displays the list of serialNumbers in a clearer view, with each subsequent serialNumbers moved to the next line.
     *
     * @param stringList The list of serialNumbers to be converted to String.
     * @return The String listing all serialNumbers in the list.
     */
    public String arrayAsString(List<Stock> stringList) {
        String serialNumbersAsString = "";
        for (int i = 0; i < stringList.size(); i++) {
            serialNumbersAsString += "\n" + stringList.get(i).getSerialNumber();
        }

        return serialNumbersAsString;
    }

}
