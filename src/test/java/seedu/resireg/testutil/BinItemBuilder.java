package seedu.resireg.testutil;

import static seedu.resireg.testutil.TypicalStudents.AMY;

import java.time.LocalDate;

import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.bin.Binnable;
import seedu.resireg.model.student.Student;

/**
 * A utility class to help with building Bin Item objects.
 */
public class BinItemBuilder {

    public static final String DEFAULT_DATE_DELETED = "2020-10-26";
    public static final Student DEFAULT_ITEM = AMY;

    private LocalDate dateDeleted;
    private Binnable item;

    /**
     * Creates a {@code BinItemBuilder} with the default details.
     */
    public BinItemBuilder() {
        dateDeleted = LocalDate.parse(DEFAULT_DATE_DELETED);
        item = AMY;
    }

    /**
     * Initializes the BinItemBuilder with the data of {@code binItemToCopy}.
     */
    public BinItemBuilder(BinItem binItemToCopy) {
        dateDeleted = binItemToCopy.getDateDeleted();
        item = binItemToCopy.getBinnedItem();
    }

    /**
     * Sets the {@code date deleted} of the {@code BinItem} that we are building.
     */
    public BinItemBuilder withDateDeleted(String dateDeleted) {
        this.dateDeleted = LocalDate.parse(dateDeleted);
        return this;
    }

    /**
     * Sets the {@code item} of the {@code BinItem} that we are building.
     */
    public BinItemBuilder withItem(Binnable item) {
        this.item = item;
        return this;
    }
    /**
     * Returns a new {@code Allocation} created.
     */
    public BinItem build() {
        return new BinItem(item, dateDeleted);
    }

}
