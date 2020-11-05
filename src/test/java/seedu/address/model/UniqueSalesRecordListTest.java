package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.model.sales.exception.DuplicateSalesRecordException;
import seedu.address.model.sales.exception.SalesRecordNotFoundException;

public class UniqueSalesRecordListTest {

    private final UniqueSalesRecordList uniqueSalesRecordList = new UniqueSalesRecordList();
    private final SalesRecordEntry entry = new SalesRecordEntry(Drink.BSBM, 100);

    @Test
    public void contains_nullSalesRecordEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSalesRecordList.contains(null));
    }

    @Test
    public void contains_salesRecordEntryNotInList_returnsFalse() {
        assertFalse(uniqueSalesRecordList.contains(entry));
    }

    @Test
    public void contains_salesRecordInList_returnsTrue() {
        uniqueSalesRecordList.add(entry);
        assertTrue(uniqueSalesRecordList.contains(entry));
    }

    @Test
    public void contains_salesRecordEntryWithSameDrinkInList_returnsTrue() {
        uniqueSalesRecordList.add(entry);
        SalesRecordEntry entryOfSameDrink =
                new SalesRecordEntry(entry.getDrink(), entry.getNumberSold() - 3);
        assertTrue(uniqueSalesRecordList.contains(entryOfSameDrink));
    }

    @Test
    public void add_nullSalesRecordEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSalesRecordList.add(null));
    }

    @Test
    public void add_salesRecordEntryWithSameDrinkDifferentNumberSold_replacesOldEntry() {
        uniqueSalesRecordList.add(entry);
        SalesRecordEntry entryOfSameDrink =
                new SalesRecordEntry(entry.getDrink(), entry.getNumberSold() - 3);
        uniqueSalesRecordList.add(entryOfSameDrink);
        assertEquals(entryOfSameDrink, uniqueSalesRecordList.getSalesEntry(entry.getDrink()));
    }

    @Test
    public void setSalesEntry_nullSalesRecordEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSalesRecordList.setSalesEntry(null));
    }

    @Test
    public void setSalesEntry_salesEntryNotInList_throwsSalesRecordNotFoundException() {
        assertThrows(SalesRecordNotFoundException.class, () -> uniqueSalesRecordList.setSalesEntry(entry));
    }

    @Test
    public void setSalesEntry_replaceSalesEntryWithNewEntryOfSameDrink_success() {
        uniqueSalesRecordList.add(entry);
        SalesRecordEntry entryOfSameDrink =
                new SalesRecordEntry(entry.getDrink(), entry.getNumberSold() - 3);
        uniqueSalesRecordList.setSalesEntry(entryOfSameDrink);
        assertNotEquals(entry, uniqueSalesRecordList.getSalesEntry(entryOfSameDrink.getDrink()));
        assertEquals(entryOfSameDrink, uniqueSalesRecordList.getSalesEntry(entryOfSameDrink.getDrink()));
    }

    @Test
    public void getSalesEntry_nullDrink_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()-> uniqueSalesRecordList.getSalesEntry(null));
    }

    @Test
    public void getSalesEntry_salesRecordEntryDoesNotExist_throwsSalesRecordNotFoundException() {
        assertThrows(SalesRecordNotFoundException.class, () -> uniqueSalesRecordList.getSalesEntry(Drink.BSBM));
    }

    @Test
    public void remove_nullSalesRecordEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSalesRecordList.remove(null));
    }

    @Test
    public void remove_salesRecordEntryDoesNotExist_throwsSalesRecordNotFoundException() {
        assertThrows(SalesRecordNotFoundException.class, () -> uniqueSalesRecordList.remove(entry));
    }

    @Test
    public void remove_existingSalesRecordEntry_removesSalesRecordEntry() {
        uniqueSalesRecordList.add(entry);
        uniqueSalesRecordList.remove(entry);
        UniqueSalesRecordList expectedUniqueSalesRecordList = new UniqueSalesRecordList();
        assertEquals(expectedUniqueSalesRecordList, uniqueSalesRecordList);
    }

    @Test
    public void isEmpty_returnsTrue() {
        assertTrue(uniqueSalesRecordList.isEmpty());
    }

    @Test
    public void size_addOneSalesRecordEntry_sizeEqualsOne() {
        uniqueSalesRecordList.add(entry);
        assertEquals(1, uniqueSalesRecordList.size());
    }

    @Test
    public void setSalesRecord_nullUniqueSalesRecordList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueSalesRecordList.setSalesRecord((UniqueSalesRecordList) null));
    }

    @Test
    public void setSalesRecord_uniqueSalesRecordList_replacesOwnListWithProvidedUniqueSalesRecordList() {
        uniqueSalesRecordList.add(entry);
        UniqueSalesRecordList expectedUniqueSalesRecordList = new UniqueSalesRecordList();
        SalesRecordEntry newEntry = new SalesRecordEntry(Drink.BSBBT, 30);
        expectedUniqueSalesRecordList.add(newEntry);
        uniqueSalesRecordList.setSalesRecord(expectedUniqueSalesRecordList);
        assertEquals(expectedUniqueSalesRecordList, uniqueSalesRecordList);
    }

    @Test
    public void setSalesRecord_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueSalesRecordList.setSalesRecord((List<SalesRecordEntry>) null));
    }

    @Test
    public void setSalesRecord_list_replacesOwnListWithProvidedList() {
        uniqueSalesRecordList.add(entry);
        SalesRecordEntry newEntry = new SalesRecordEntry(Drink.BSBBT, 30);
        List<SalesRecordEntry> salesRecordEntryList = Collections.singletonList(newEntry);
        uniqueSalesRecordList.setSalesRecord(salesRecordEntryList);
        UniqueSalesRecordList expectedUniqueSalesRecordList = new UniqueSalesRecordList();
        expectedUniqueSalesRecordList.add(newEntry);
        assertEquals(expectedUniqueSalesRecordList, uniqueSalesRecordList);
    }

    @Test
    public void setSalesRecord_listWithDuplicateSalesRecord_throwsDuplicateSalesRecordException() {
        List<SalesRecordEntry> listWithDuplicateSalesRecord = Arrays.asList(entry, entry);
        assertThrows(DuplicateSalesRecordException.class, () ->
                uniqueSalesRecordList.setSalesRecord(listWithDuplicateSalesRecord));
    }

    @Test
    public void setSalesRecord_nullMap_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueSalesRecordList.setSalesRecord((Map<Drink, Integer>) null));
    }

    @Test
    public void setSalesRecord_map_replacesOwnListWithProvidedMap() {
        uniqueSalesRecordList.add(entry);
        SalesRecordEntry newEntry = new SalesRecordEntry(Drink.BSBBT, 30);
        Map<Drink, Integer> salesMap = Collections.singletonMap(Drink.BSBBT, 30);
        uniqueSalesRecordList.setSalesRecord(salesMap);
        UniqueSalesRecordList expectedUniqueSalesRecordList = new UniqueSalesRecordList();
        expectedUniqueSalesRecordList.add(newEntry);
        assertEquals(expectedUniqueSalesRecordList, uniqueSalesRecordList);
    }

    @Test
    public void sort_correctlySortsInDescendingOrder() {
        SalesRecordEntry entryTwo = new SalesRecordEntry(Drink.BSBBT, 30);
        SalesRecordEntry entryThree = new SalesRecordEntry(Drink.BSBGT, 70);

        uniqueSalesRecordList.add(entry);
        uniqueSalesRecordList.add(entryTwo);
        uniqueSalesRecordList.add(entryThree);

        uniqueSalesRecordList.sort();

        UniqueSalesRecordList sortedList = new UniqueSalesRecordList();
        sortedList.add(entry);
        sortedList.add(entryThree);
        sortedList.add(entryTwo);

        assertEquals(sortedList, uniqueSalesRecordList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueSalesRecordList.asUnmodifiableObservableList().remove(0));
    }

}
