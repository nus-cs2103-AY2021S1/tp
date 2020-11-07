package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Drink;
import seedu.address.model.SalesBook;
import seedu.address.model.SalesRecordEntry;

//@@author ureshiiYing
/**
 * A utility class containing a list of {@code SalesRecordEntry} objects to be used in tests.
 */
public class TypicalSalesRecordEntries {
    public static final SalesRecordEntry BSBM = new SalesRecordEntry(Drink.BSBM, 100);
    public static final SalesRecordEntry BSBBT = new SalesRecordEntry(Drink.BSBBT, 50);
    public static final SalesRecordEntry BSBGT = new SalesRecordEntry(Drink.BSBGT, 40);
    public static final SalesRecordEntry BSPM = new SalesRecordEntry(Drink.BSPM, 0);
    public static final SalesRecordEntry BSPBT = new SalesRecordEntry(Drink.BSPBT, 0);
    public static final SalesRecordEntry BSPGT = new SalesRecordEntry(Drink.BSPGT, 0);

    private TypicalSalesRecordEntries() {} // prevents instantiation

    public static SalesBook getTypicalSalesBook() {
        SalesBook sb = new SalesBook();
        for (SalesRecordEntry s : getTypicalSalesRecordEntries()) {
            sb.addSalesRecordEntry(s);
        }
        return sb;
    }

    public static List<SalesRecordEntry> getTypicalSalesRecordEntries() {
        return new ArrayList<>(Arrays.asList(BSBM, BSBBT, BSBGT, BSPM, BSPBT, BSPGT));
    }
}
