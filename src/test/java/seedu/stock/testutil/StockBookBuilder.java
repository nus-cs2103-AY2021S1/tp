package seedu.stock.testutil;

import seedu.stock.model.StockBook;
import seedu.stock.model.stock.Stock;

/**
 * A utility class to help with building Stockbook objects.
 * Example usage: <br>
 *     {@code StockBook sb = new StockBookBuilder().withStock(apple).build();}
 */
public class StockBookBuilder {

    private StockBook stockBook;

    public StockBookBuilder() {
        stockBook = new StockBook();
    }

    public StockBookBuilder(StockBook stockBook) {
        this.stockBook = stockBook;
    }

    /**
     * Adds a new {@code stock} to the {@code StockBook} that we are building.
     */
    public StockBookBuilder withStock(Stock stock) {
        stockBook.addStock(stock);
        return this;
    }

    public StockBook build() {
        return stockBook;
    }
}
