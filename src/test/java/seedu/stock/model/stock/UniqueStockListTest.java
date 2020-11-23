package seedu.stock.model.stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_LOCATION_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;
import static seedu.stock.testutil.Assert.assertThrows;
import static seedu.stock.testutil.TypicalStocks.APPLE;
import static seedu.stock.testutil.TypicalStocks.BANANA;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.stock.model.stock.exceptions.DuplicateStockException;
import seedu.stock.model.stock.exceptions.StockNotFoundException;
import seedu.stock.testutil.StockBuilder;

public class UniqueStockListTest {

    private final UniqueStockList uniqueStockList = new UniqueStockList();

    @Test
    public void contains_nullStock_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStockList.contains(null));
    }

    @Test
    public void contains_stockNotInList_returnsFalse() {
        assertFalse(uniqueStockList.contains(APPLE));
    }

    @Test
    public void contains_stockInList_returnsTrue() {
        uniqueStockList.add(APPLE);
        assertTrue(uniqueStockList.contains(APPLE));
    }

    @Test
    public void contains_stockWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStockList.add(APPLE);
        Stock editedApple = new StockBuilder(APPLE).withQuantity(VALID_QUANTITY_BANANA)
                .withLocation(VALID_LOCATION_BANANA)
                .build();
        assertTrue(uniqueStockList.contains(editedApple));
    }

    @Test
    public void add_nullStock_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStockList.add(null));
    }

    @Test
    public void add_duplicateStock_throwsDuplicateStockException() {
        uniqueStockList.add(APPLE);
        assertThrows(DuplicateStockException.class, () -> uniqueStockList.add(APPLE));
    }

    @Test
    public void setStock_nullTargetStock_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStockList.setStock(null, APPLE));
    }

    @Test
    public void setStock_nullEditedStock_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStockList.setStock(APPLE, null));
    }

    @Test
    public void setStock_targetStockNotInList_throwsStockNotFoundException() {
        assertThrows(StockNotFoundException.class, () -> uniqueStockList.setStock(APPLE, APPLE));
    }

    @Test
    public void setStock_editedStockIsSameStock_success() {
        uniqueStockList.add(APPLE);
        uniqueStockList.setStock(APPLE, APPLE);
        UniqueStockList expectedUniqueStockList = new UniqueStockList();
        expectedUniqueStockList.add(APPLE);
        assertEquals(expectedUniqueStockList, uniqueStockList);
    }

    @Test
    public void setStock_editedStockHasSameIdentity_success() {
        uniqueStockList.add(APPLE);
        Stock editedApple = new StockBuilder(APPLE).withLocation(VALID_LOCATION_BANANA)
                .withQuantity(VALID_QUANTITY_BANANA)
                .build();
        uniqueStockList.setStock(APPLE, editedApple);
        UniqueStockList expectedUniqueStockList = new UniqueStockList();
        expectedUniqueStockList.add(editedApple);
        assertEquals(expectedUniqueStockList, uniqueStockList);
    }

    @Test
    public void setStock_editedStockHasDifferentIdentity_success() {
        uniqueStockList.add(APPLE);
        uniqueStockList.setStock(APPLE, BANANA);
        UniqueStockList expectedUniqueStockList = new UniqueStockList();
        expectedUniqueStockList.add(BANANA);
        assertEquals(expectedUniqueStockList, uniqueStockList);
    }

    @Test
    public void setStock_editedStockHasNonUniqueIdentity_throwsDuplicateStockException() {
        uniqueStockList.add(APPLE);
        uniqueStockList.add(BANANA);
        assertThrows(DuplicateStockException.class, () -> uniqueStockList.setStock(APPLE, BANANA));
    }

    @Test
    public void remove_nullStock_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStockList.remove(null));
    }

    @Test
    public void remove_stockDoesNotExist_throwsStockNotFoundException() {
        assertThrows(StockNotFoundException.class, () -> uniqueStockList.remove(APPLE));
    }

    @Test
    public void remove_existingStock_removesStock() {
        uniqueStockList.add(APPLE);
        uniqueStockList.remove(APPLE);
        UniqueStockList expectedUniqueStockList = new UniqueStockList();
        assertEquals(expectedUniqueStockList, uniqueStockList);
    }

    @Test
    public void setStocks_nullUniqueStockList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStockList.setStocks((UniqueStockList) null));
    }

    @Test
    public void setStocks_uniqueStockList_replacesOwnListWithProvidedUniqueStockList() {
        uniqueStockList.add(APPLE);
        UniqueStockList expectedUniqueStockList = new UniqueStockList();
        expectedUniqueStockList.add(BANANA);
        uniqueStockList.setStocks(expectedUniqueStockList);
        assertEquals(expectedUniqueStockList, uniqueStockList);
    }

    @Test
    public void setStocks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStockList.setStocks((List<Stock>) null));
    }

    @Test
    public void setStocks_list_replacesOwnListWithProvidedList() {
        uniqueStockList.add(APPLE);
        List<Stock> stockList = Collections.singletonList(BANANA);
        uniqueStockList.setStocks(stockList);
        UniqueStockList expectedUniqueStockList = new UniqueStockList();
        expectedUniqueStockList.add(BANANA);
        assertEquals(expectedUniqueStockList, uniqueStockList);
    }

    @Test
    public void setStocks_listWithDuplicateStocks_throwsDuplicateStockException() {
        List<Stock> listWithDuplicateStocks = Arrays.asList(APPLE, APPLE);
        assertThrows(DuplicateStockException.class, () -> uniqueStockList.setStocks(listWithDuplicateStocks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, (
                ) -> uniqueStockList.asUnmodifiableObservableList().remove(0));
    }
}
