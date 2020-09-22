package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.stock.Stock;

/**
 * Unmodifiable view of an stock book
 */
public interface ReadOnlyStockBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Stock> getStockList();

}
