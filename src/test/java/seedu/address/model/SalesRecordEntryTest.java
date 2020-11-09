package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalSalesRecordEntries;

public class SalesRecordEntryTest {

    private final SalesRecordEntry salesRecordEntry = TypicalSalesRecordEntries.BSBBT;

    @Test
    public void isSameRecord_sameDrinkDifferentNumberSold_returnsTrue() {
        SalesRecordEntry newEntry = new
                SalesRecordEntry(salesRecordEntry.getDrink(), salesRecordEntry.getNumberSold() - 1);
        assertTrue(salesRecordEntry.isSameRecord(newEntry));
    }

    @Test
    public void isSameRecord_differentDrink_returnsFalse() {
        SalesRecordEntry newEntry = TypicalSalesRecordEntries.BSBGT;
        assertFalse(salesRecordEntry.isSameRecord(newEntry));
    }

    @Test
    public void equals() {
        // same values -> returns true
        assertTrue(salesRecordEntry.equals(TypicalSalesRecordEntries.BSBBT));

        // same object -> returns true
        assertTrue(salesRecordEntry.equals(salesRecordEntry));

        // null -> returns false
        assertFalse(salesRecordEntry.equals(null));

        // different type -> returns false
        assertFalse(salesRecordEntry.equals(5));

        // different entry -> returns false
        assertFalse(salesRecordEntry.equals(TypicalSalesRecordEntries.BSBGT));

        // different Drink -> returns false
        assertFalse(salesRecordEntry.equals(new SalesRecordEntry(Drink.BSBGT, salesRecordEntry.getNumberSold())));

        // different NumberSold -> returns false
        assertFalse(salesRecordEntry.equals(new SalesRecordEntry(salesRecordEntry.getDrink(),
                salesRecordEntry.getNumberSold() - 3)));
    }
}
