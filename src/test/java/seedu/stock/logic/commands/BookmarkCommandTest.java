package seedu.stock.logic.commands;

import static seedu.stock.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.stock.testutil.TypicalSerialNumberSets.getTypicalSerialNumberSetsBook;
import static seedu.stock.testutil.TypicalStocks.INDEX_FIRST_STOCK;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_FIRST_STOCK;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.UserPrefs;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;
import seedu.stock.testutil.StockBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code BookmarkCommand}.
 */
public class BookmarkCommandTest {
    private Model model = new ModelManager(getTypicalStockBook(), new UserPrefs(),
            getTypicalSerialNumberSetsBook());



    @Test
    public void execute_bookmarkAlreadyBookmarked_throwsCommandException() {
        Set<SerialNumber> serialNumbersToBookmark = new LinkedHashSet<>();
        List<Stock> stocksToBookmark = new ArrayList<>();

        Stock firstStock = model.getFilteredStockList().get(INDEX_FIRST_STOCK.getZeroBased());

        serialNumbersToBookmark.add(SERIAL_NUMBER_FIRST_STOCK);
        stocksToBookmark.add(firstStock);

        BookmarkCommand bookmarkCommand = new BookmarkCommand(serialNumbersToBookmark);

        String expectedMessage = String.format(BookmarkCommand.MESSAGE_ALREADY_BOOKMARKED,
                stocksAsString(stocksToBookmark));

        assertCommandFailure(bookmarkCommand, model, expectedMessage);
    }


    @Test
    public void execute_bookmarkInvalidSerialNumber_throwsCommandException() {
        Set<SerialNumber> serialNumbersToBookmark = new LinkedHashSet<>();
        List<Stock> stocksToBookmark = new ArrayList<>();
        SerialNumber invalidSerialNumber = new SerialNumber("ABC1");

        Stock firstStock = model.getFilteredStockList().get(INDEX_FIRST_STOCK.getZeroBased());
        Stock invalidSerialNumberStock = new StockBuilder(firstStock).copyOfStockBuilder()
                .withSerialNumber("ABC1").build();

        serialNumbersToBookmark.add(invalidSerialNumber);
        stocksToBookmark.add(invalidSerialNumberStock);

        BookmarkCommand bookmarkCommand = new BookmarkCommand(serialNumbersToBookmark);

        String expectedMessage = String.format(BookmarkCommand.MESSAGE_SERIAL_NUMBER_NOT_FOUND,
                arrayAsString(stocksToBookmark));

        assertCommandFailure(bookmarkCommand, model, expectedMessage);
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
     * Displays the list of serialNumbers in a clearer view, with each subsequent
     * serialNumbers moved to the next line.
     * @param stringList The list of serialNumbers to be converted to String.
     * @return The String listing all serialNumbers in the list.
     */
    public String arrayAsString(List<Stock> stringList) {
        String serialNumbersAsString = "";
        for (int i = 0; i < stringList.size(); i++) {
            serialNumbersAsString += "\n" + stringList.get(i).getSerialNumber();
        }
        serialNumbersAsString += "\n";

        return serialNumbersAsString;
    }

}
