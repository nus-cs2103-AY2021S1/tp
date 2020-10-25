package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SalesRecordEntryTest {

    private final SalesRecordEntry salesRecordEntry = new SalesRecordEntry(Drink.BSBBT, 42);

    @Test
    public void isSameRecord_sameDrinkDifferentNumberSold_returnsTrue() {
        SalesRecordEntry newEntry = new
                SalesRecordEntry(salesRecordEntry.getDrink(), salesRecordEntry.getNumberSold() - 1);
        assertTrue(salesRecordEntry.isSameRecord(newEntry));
    }

    @Test
    public void isSameRecord_differentDrink_returnsFalse() {
        SalesRecordEntry newEntry = new SalesRecordEntry(Drink.BSBGT, 40);
        assertFalse(salesRecordEntry.isSameRecord(newEntry));
    }

    @Test
    public void equals() {
        // same values -> returns true
        SalesRecordEntry sameEntry = new SalesRecordEntry(salesRecordEntry.getDrink(),
                salesRecordEntry.getNumberSold());
        assertTrue(salesRecordEntry.equals(sameEntry));

        // same object -> returns true
        assertTrue(salesRecordEntry.equals(salesRecordEntry));

        // null -> returns false
        assertFalse(salesRecordEntry.equals(null));

        // different type -> returns false
        assertFalse(salesRecordEntry.equals(5));

        // different entry -> returns false
        SalesRecordEntry differentEntry = new SalesRecordEntry(Drink.BSBGT, 32);
        assertFalse(salesRecordEntry.equals(differentEntry));

        // different Drink -> returns false
        assertFalse(salesRecordEntry.equals(new SalesRecordEntry(Drink.BSBGT, salesRecordEntry.getNumberSold())));

        // different NumberSold -> returns false
        assertFalse(salesRecordEntry.equals(new SalesRecordEntry(salesRecordEntry.getDrink(),
                salesRecordEntry.getNumberSold() - 3)));
    }
}
