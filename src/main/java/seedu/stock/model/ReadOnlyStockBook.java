package seedu.stock.model;

import javafx.collections.ObservableList;
import seedu.stock.model.stock.Stock;

/**
 * Unmodifiable view of an stock book
 */
public interface ReadOnlyStockBook {

    /**
     * Returns an unmodifiable view of the stocks list.
     * This list will not contain any duplicate stocks.
     */
    ObservableList<Stock> getStockList();

}
