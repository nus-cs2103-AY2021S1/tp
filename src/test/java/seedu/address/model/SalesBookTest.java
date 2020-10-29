package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SalesBookTest {

    private final SalesBook salesBook = new SalesBook();
    private final HashMap<Drink, Integer> sales = new HashMap<>();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), salesBook.getSalesRecord());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> salesBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlySalesBook_replacesData() {
        SalesBook newData = new SalesBook();
        SalesRecordEntry newEntry = new SalesRecordEntry(Drink.BSBGT, 10);
        List<SalesRecordEntry> salesRecordEntryList = Collections.singletonList(newEntry);
        newData.setRecord(salesRecordEntryList);

        salesBook.resetData(newData);
        assertEquals(newData, salesBook);
    }

    @Test
    public void setRecord_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> salesBook.setRecord(null));
    }

    @Test
    public void setRecord_list_replacesOwnUniqueListWithProvidedList() {
        SalesBook newData = new SalesBook();
        SalesRecordEntry newEntry = new SalesRecordEntry(Drink.BSBGT, 10);
        List<SalesRecordEntry> salesRecordEntryList = Collections.singletonList(newEntry);
        newData.setRecord(salesRecordEntryList);

        assertEquals(salesRecordEntryList, newData.getSalesRecord());
    }

    @Test
    public void overwriteSales_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> salesBook.overwriteSales(null));
    }

    @Test
    public void overwriteSales_overwriteEmptySalesBookWithOneDrinkItem_success() {
        sales.put(Drink.BSBM, 90);
        salesBook.overwriteSales(sales);

        sales.put(Drink.BSBBT, 0);
        sales.put(Drink.BSBGT, 0);
        sales.put(Drink.BSPM, 0);
        sales.put(Drink.BSPGT, 0);
        sales.put(Drink.BSPBT, 0);

        UniqueSalesRecordList expectedSalesRecord = new UniqueSalesRecordList();
        expectedSalesRecord.setSalesRecord(sales);

        assertEquals(expectedSalesRecord, salesBook.getRecord());
    }

    @Test
    public void overwriteSales_overwriteNonEmptySalesBookWithOneDrinkItem_success() {
        // initialise sales book
        sales.put(Drink.BSBM, 90);
        salesBook.overwriteSales(sales);

        // overwrite an initialised sales book
        HashMap<Drink, Integer> newSales = new HashMap<>();
        newSales.put(Drink.BSBM, 45);
        salesBook.overwriteSales(newSales);

        newSales.put(Drink.BSBBT, 0);
        newSales.put(Drink.BSBGT, 0);
        newSales.put(Drink.BSPM, 0);
        newSales.put(Drink.BSPGT, 0);
        newSales.put(Drink.BSPBT, 0);

        UniqueSalesRecordList expectedSalesRecord = new UniqueSalesRecordList();
        expectedSalesRecord.setSalesRecord(newSales);

        assertEquals(expectedSalesRecord, salesBook.getRecord());
    }

    @Test
    public void isEmptySalesBook() {
        assertTrue(salesBook.isEmptySalesRecord());
    }

    @Test
    public void hasSalesRecordEntry_nullSalesRecordEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> salesBook.hasSalesRecordEntry(null));
    }

    @Test
    public void hasSalesRecordEntry_entryNotInSalesBook_returnsFalse() {
        SalesRecordEntry entry = new SalesRecordEntry(Drink.BSBGT, 49);
        assertFalse(salesBook.hasSalesRecordEntry(entry));
    }

    @Test
    public void hasSalesRecordEntry_entryInSalesBook_returnsTrue() {
        SalesRecordEntry entry = new SalesRecordEntry(Drink.BSBGT, 49);
        salesBook.addSalesRecordEntry(entry);
        assertTrue(salesBook.hasSalesRecordEntry(entry));
    }

    @Test
    public void hasSalesRecordEntry_entryWithSameDrinkFieldInSalesBook_returnsTrue() {
        SalesRecordEntry entry = new SalesRecordEntry(Drink.BSBGT, 49);
        salesBook.addSalesRecordEntry(entry);
        SalesRecordEntry sameDrinkEntry = new SalesRecordEntry(entry.getDrink(), entry.getNumberSold() - 1);
        assertTrue(salesBook.hasSalesRecordEntry(sameDrinkEntry));
    }

    @Test
    public void getSalesRecordList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> salesBook.getSalesRecord().remove(0));
    }

    @Test
    public void equals() {
        SalesBook sameBook = new SalesBook();
        SalesRecordEntry newEntry = new SalesRecordEntry(Drink.BSBGT, 10);
        List<SalesRecordEntry> salesRecordEntryList = Collections.singletonList(newEntry);
        sameBook.setRecord(salesRecordEntryList);

        salesBook.setRecord(salesRecordEntryList);

        SalesBook differentBook = new SalesBook();
        sales.put(Drink.BSPGT, 20);
        differentBook.overwriteSales(sales);

        // same values -> returns true
        assertTrue(salesBook.equals(sameBook));

        // same object -> returns true
        assertTrue(salesBook.equals(salesBook));

        // null -> returns false
        assertFalse(salesBook.equals(null));

        // different types -> returns false
        assertFalse(salesBook.equals(sales));

        // different salesBook -> returns false
        assertFalse(salesBook.equals(differentBook));
    }
}
