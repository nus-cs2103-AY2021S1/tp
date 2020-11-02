package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Drink;
import seedu.address.model.SalesBook;
import seedu.address.model.SalesRecordEntry;

public class TypicalSalesRecordEntries {
    public static final SalesRecordEntry BSBGT = new SalesRecordEntry(Drink.BSBGT,
            0);
    public static final SalesRecordEntry BSPGT = new SalesRecordEntry(Drink.BSBGT,
            0);
    public static final SalesRecordEntry BSPBT = new SalesRecordEntry(Drink.BSPBT,
            0);
    public static final SalesRecordEntry BSBBT = new SalesRecordEntry(Drink.BSBBT,
            0);
    public static final SalesRecordEntry BSPM = new SalesRecordEntry(Drink.BSPM,
            0);
    public static final SalesRecordEntry BSBM = new SalesRecordEntry(Drink.BSBM,
            0);

    public static final SalesRecordEntry UPDATED_BSBGT = new SalesRecordEntry(Drink.BSBGT,
            10);
    public static final SalesRecordEntry UPDATED_BSPGT = new SalesRecordEntry(Drink.BSBGT,
            10);
    public static final SalesRecordEntry UPDATED_BSPBT = new SalesRecordEntry(Drink.BSPBT,
            20);
    public static final SalesRecordEntry UPDATED_BSBBT = new SalesRecordEntry(Drink.BSBBT,
            30);
    public static final SalesRecordEntry UPDATED_BSPM = new SalesRecordEntry(Drink.BSPM,
            40);
    public static final SalesRecordEntry UPDATED_BSBM = new SalesRecordEntry(Drink.BSBM,
            50);

    private TypicalSalesRecordEntries() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static SalesBook getTypicalSalesBook() {
        SalesBook ab = new SalesBook();
        for (SalesRecordEntry salesRecordEntry : getTypicalSalesRecordEntries()) {
            ab.addSalesRecordEntry(salesRecordEntry);
        }
        return ab;
    }

    public static List<SalesRecordEntry> getTypicalSalesRecordEntries() {
        return new ArrayList<>(Arrays.asList(BSBGT, BSPGT, BSPBT, BSBBT, BSPM, BSBM));
    }
}
