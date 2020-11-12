package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalSalesRecordEntries;

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
        SalesBook newData = TypicalSalesRecordEntries.getTypicalSalesBook();
        salesBook.resetData(newData);
        assertEquals(newData, salesBook);
    }

    @Test
    public void setRecord_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> salesBook.setRecord(null));
    }

    @Test
    public void setRecord_list_replacesOwnUniqueListWithProvidedList() {
        SalesRecordEntry newEntry = new SalesRecordEntry(Drink.BSBGT, 10);
        List<SalesRecordEntry> salesRecordEntryList = Collections.singletonList(newEntry);

        salesBook.setRecord(salesRecordEntryList);

        assertEquals(salesRecordEntryList, salesBook.getSalesRecord());
    }

    @Test
    public void overwriteSales_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> salesBook.overwriteSales(null));
    }

    @Test
    public void overwriteSales_overwriteSalesBookWithOneDrinkItem_success() {
        // overwrite empty sales book
        sales.put(Drink.BSBM, 90);
        salesBook.overwriteSales(sales);

        sales.put(Drink.BSBBT, 0);
        sales.put(Drink.BSBGT, 0);
        sales.put(Drink.BSPM, 0);
        sales.put(Drink.BSPGT, 0);
        sales.put(Drink.BSPBT, 0);

        UniqueSalesRecordList expectedSalesRecordList = new UniqueSalesRecordList();
        expectedSalesRecordList.setSalesRecord(sales);

        assertEquals(expectedSalesRecordList, salesBook.getRecord());

        // overwrite existing sales book
        sales.put(Drink.BSBM, 45);
        salesBook.overwriteSales(sales);

        UniqueSalesRecordList newSalesRecordList = new UniqueSalesRecordList();
        newSalesRecordList.setSalesRecord(sales);

        assertEquals(newSalesRecordList, salesBook.getRecord());
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
        SalesRecordEntry entry = TypicalSalesRecordEntries.BSBGT;
        assertFalse(salesBook.hasSalesRecordEntry(entry));
    }

    @Test
    public void hasSalesRecordEntry_entryInSalesBook_returnsTrue() {
        SalesRecordEntry entry = TypicalSalesRecordEntries.BSBGT;
        salesBook.addSalesRecordEntry(entry);
        assertTrue(salesBook.hasSalesRecordEntry(entry));
    }

    @Test
    public void hasSalesRecordEntry_entryWithSameDrinkFieldInSalesBook_returnsTrue() {
        SalesRecordEntry entry = TypicalSalesRecordEntries.BSBGT;
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
        SalesRecordEntry newEntry = TypicalSalesRecordEntries.BSBGT;
        List<SalesRecordEntry> salesRecordEntryList = Collections.singletonList(newEntry);

        sameBook.setRecord(salesRecordEntryList);
        salesBook.setRecord(salesRecordEntryList);

        SalesBook differentBook = TypicalSalesRecordEntries.getTypicalSalesBook();

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
