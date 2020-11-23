package seedu.stock.testutil;

import seedu.stock.model.stock.AccumulatedQuantity;
import seedu.stock.model.stock.SerialNumberSet;
import seedu.stock.model.stock.Source;

/**
 * A utility class to help with building SerialNumberSet objects.
 */
public class SerialNumberSetBuilder {

    public static final String DEFAULT_SOURCE = "Fairprice";
    public static final String DEFAULT_ACCUMULATED_QUANTITY = "0";

    private Source source;
    private AccumulatedQuantity accumulatedQuantity;

    /**
     * Creates a {@code SerialNumberSetBuilder} with the default details.
     */
    public SerialNumberSetBuilder() {
        source = new Source(DEFAULT_SOURCE);
        accumulatedQuantity = new AccumulatedQuantity(DEFAULT_ACCUMULATED_QUANTITY);
    }

    /**
     * Initializes the SerialNumberSetBuilder with the data of {@code serialNumberSetToCopy}.
     */
    public SerialNumberSetBuilder(SerialNumberSet serialNumberSetToCopy) {
        source = serialNumberSetToCopy.getSource();
        accumulatedQuantity = serialNumberSetToCopy.getAccumulatedQuantity();
    }

    /**
     * Sets the {@code Source} of the {@code SerialNumberSet} that we are building.
     */
    public SerialNumberSetBuilder withSource(String source) {
        this.source = new Source(source);
        return this;
    }

    /**
     * Sets the {@code AccumulatedQuantity} of the {@code Stock} that we are building.
     */
    public SerialNumberSetBuilder withAccumulatedQuantity(String accumulatedQuantity) {
        this.accumulatedQuantity = new AccumulatedQuantity(accumulatedQuantity);
        return this;
    }

    public SerialNumberSet build() {
        return new SerialNumberSet(source, accumulatedQuantity);
    }

}
