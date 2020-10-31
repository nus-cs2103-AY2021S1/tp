package seedu.stock.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.stock.commons.util.SortUtil;
import seedu.stock.model.StockBook;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;

/**
 * A utility class containing a list of {@code Stock} objects to be used in tests.
 */
public class TypicalStocks {
    public static final Stock APPLE = new StockBuilder().withName("apple juice")
            .withSerialNumber("ntuc1").withSource("ntuc")
            .withQuantity("2000", "0").withLocation("fruit section, subsection c")
            .build();
    public static final Stock BANANA = new StockBuilder().withName("banana cake")
            .withSerialNumber("fairprice1").withSource("fairprice")
            .withQuantity("2103", "2200").withLocation("fruits section, subsection b")
            .build();
    public static final Stock PINEAPPLE = new StockBuilder().withName("pineapple tart")
            .withSerialNumber("bengawansolo1").withSource("bengawansolo")
            .withQuantity("123").withLocation("tart section, subsection a")
            .build();
    public static final Stock ORANGE = new StockBuilder().withName("orange")
            .withSerialNumber("giant1").withSource("giant")
            .withQuantity("111").withLocation("fruit section, subsection x")
            .build();
    public static final SerialNumber SERIAL_NUMBER_FIRST_STOCK = APPLE.getSerialNumber();
    public static final SerialNumber SERIAL_NUMBER_SECOND_STOCK = BANANA.getSerialNumber();
    public static final SerialNumber SERIAL_NUMBER_THIRD_STOCK = PINEAPPLE.getSerialNumber();
    public static final SerialNumber SERIAL_NUMBER_FOURTH_STOCK = ORANGE.getSerialNumber();
    public static final SerialNumber UNKNOWN_SERIAL_NUMBER = new SerialNumber("unknown1");

    public static final String RESERVED_NON_EXISTENCE_SOURCE = "$non-existent-source";

    private TypicalStocks() {} // prevents instantiation

    /**
     * Returns a {@code StockBook} with all the typical stocks.
     */
    public static StockBook getTypicalStockBook() {
        StockBook inventory = new StockBook();
        for (Stock stock : getTypicalStocks()) {
            inventory.addStock(stock);
        }
        return inventory;
    }

    /**
     * Returns a {@code StockBook} with all the typical stocks sorted by serial number.
     * @return StockBook The StockBook containing typical stocks.
     */
    public static StockBook getTypicalStockBookSortedSerialNumber() {
        List<Stock> typicalStocks = getTypicalStocks();
        typicalStocks.sort(SortUtil.generateComparator(SortUtil.Field.SERIALNUMBER));
        StockBook inventory = new StockBook();
        for (Stock stock : typicalStocks) {
            inventory.addStock(stock);
        }
        return inventory;
    }

    public static List<Stock> getTypicalStocks() {
        return new ArrayList<>(Arrays.asList(APPLE, BANANA, ORANGE));
    }

    /**
     * Displays the list of stocks in a clearer view, with each subsequent stock moved to the next line.
     *
     * @param stockList The list of stocks to convert to String.
     * @return The String depicting each stock in the list.
     */
    public static String stocksAsString(List<Stock> stockList) {
        String stocksAsString = "";
        for (int i = 0; i < stockList.size(); i++) {
            stocksAsString += "\n" + stockList.get(i).toString();
        }
        return stockList.size() == 0 ? "No stocks deleted" : stocksAsString;
    }

    /**
     * Displays the list of serial numbers in a clearer view, with each subsequent serial number moved
     * to the next line.
     *
     * @param serialNumberList The list of serial numbers to convert to String.
     * @return The String depicting each serial number in the list.
     */
    public static String serialNumberListAsString(List<SerialNumber> serialNumberList) {
        String serialNumbersAsString = "";
        for (int i = 0; i < serialNumberList.size(); i++) {
            serialNumbersAsString += "\n" + serialNumberList.get(i).toString();
        }
        return serialNumbersAsString;
    }
}
