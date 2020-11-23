package seedu.stock.testutil;

import seedu.stock.model.SerialNumberSetsBook;
import seedu.stock.model.stock.SerialNumberSet;

/**
 * A utility class to help with building SerialNumberSetsBook objects.
 * Example usage: <br>
 *     {@code SerialNumberSetsBook sb = new SerialNumberSetsBookBuilder()
 *     .withSerialNumberSets(serialNumberSet).build();}
 */
public class SerialNumberSetsBookBuilder {

    private SerialNumberSetsBook serialNumberSetsBook;

    public SerialNumberSetsBookBuilder() {
        serialNumberSetsBook = new SerialNumberSetsBook();
    }

    public SerialNumberSetsBookBuilder(SerialNumberSetsBook serialNumberSetsBook) {
        this.serialNumberSetsBook = serialNumberSetsBook;
    }

    /**
     * Adds a new {@code serialNumberSet} to the {@code SerialNumberSetsBook} that we are building.
     */
    public SerialNumberSetsBookBuilder withSerialNumberSet(SerialNumberSet serialNumberSet) {
        serialNumberSetsBook.addSerialNumberSet(serialNumberSet);
        return this;
    }

    public SerialNumberSetsBook build() {
        return serialNumberSetsBook;
    }
}
