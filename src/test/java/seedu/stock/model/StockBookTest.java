package seedu.stock.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_SOURCE_BANANA;
import static seedu.stock.testutil.Assert.assertThrows;
import static seedu.stock.testutil.TypicalStocks.APPLE;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.stock.model.stock.Stock;
import seedu.stock.model.stock.exceptions.DuplicateStockException;
import seedu.stock.testutil.StockBuilder;

public class StockBookTest {

    private final StockBook stockBook = new StockBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), stockBook.getStockList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> stockBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyStockBook_replacesData() {
        StockBook newData = getTypicalStockBook();
        stockBook.resetData(newData);
        assertEquals(newData, stockBook);
    }

    @Test
    public void resetData_withDuplicateStocks_throwsDuplicateStockException() {
        // Two stocks with the same identity fields
        Stock editedApple = new StockBuilder(APPLE).withSource(VALID_SOURCE_BANANA).build();
        List<Stock> newStocks = Arrays.asList(APPLE, editedApple);
        StockBookStub newData = new StockBookStub(newStocks);

        assertThrows(DuplicateStockException.class, () -> stockBook.resetData(newData));
    }

    @Test
    public void hasStock_nullStock_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> stockBook.hasStock(null));
    }

    @Test
    public void hasStock_stockNotInStockBook_returnsFalse() {
        assertFalse(stockBook.hasStock(APPLE));
    }

    @Test
    public void hasStock_stockInStockBook_returnsTrue() {
        stockBook.addStock(APPLE);
        assertTrue(stockBook.hasStock(APPLE));
    }

    @Test
    public void hasStock_stockWithSameIdentityFieldsInStockBook_returnsTrue() {
        stockBook.addStock(APPLE);
        Stock editedApple = new StockBuilder(APPLE).withSource(VALID_SOURCE_BANANA).build();
        assertTrue(stockBook.hasStock(editedApple));
    }

    @Test
    public void getStockList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> stockBook.getStockList().remove(0));
    }

    /**
     * A stub ReadOnlyStockBook whose stocks list can violate interface constraints.
     */
    private static class StockBookStub implements ReadOnlyStockBook {
        private final ObservableList<Stock> stocks = FXCollections.observableArrayList();

        StockBookStub(Collection<Stock> stocks) {
            this.stocks.setAll(stocks);
        }

        @Override
        public ObservableList<Stock> getStockList() {
            return stocks;
        }
    }
}
