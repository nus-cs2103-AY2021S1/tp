package seedu.stock.testutil;

import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Source;
import seedu.stock.model.stock.Stock;

/**
 * A utility class to help with building Stock objects.
 */
public class StockBuilder {

    public static final String DEFAULT_NAME = "Banana Bun";
    public static final String DEFAULT_SERIALNUMBER = "Fairprice1";
    public static final String DEFAULT_SOURCE = "Fairprice";
    public static final String DEFAULT_QUANTITY = "1000";
    public static final String DEFAULT_LOW_QUANTITY = "0";
    public static final String DEFAULT_LOCATION = "Fruits section, Subsection B";

    private Name name;
    private SerialNumber serialNumber;
    private Source source;
    private Quantity quantity;
    private Location location;

    /**
     * Creates a {@code StockBuilder} with the default details.
     */
    public StockBuilder() {
        name = new Name(DEFAULT_NAME);
        serialNumber = new SerialNumber(DEFAULT_SERIALNUMBER);
        source = new Source(DEFAULT_SOURCE);
        quantity = new Quantity(DEFAULT_QUANTITY, DEFAULT_LOW_QUANTITY);
        location = new Location(DEFAULT_LOCATION);
    }

    /**
     * Initializes the StockBuilder with the data of {@code stockToCopy}.
     */
    public StockBuilder(Stock stockToCopy) {
        name = stockToCopy.getName();
        serialNumber = stockToCopy.getSerialNumber();
        source = stockToCopy.getSource();
        quantity = stockToCopy.getQuantity();
        location = stockToCopy.getLocation();
    }

    /**
     * Sets the {@code Name} of the {@code Stock} that we are building.
     */
    public StockBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code SerialNumber} of the {@code Stock} that we are building.
     */
    public StockBuilder withSerialNumber(String serialNumber) {
        this.serialNumber = new SerialNumber(serialNumber);
        return this;
    }

    /**
     * Sets the {@code Source} of the {@code Stock} that we are building.
     */
    public StockBuilder withSource(String source) {
        this.source = new Source(source);
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Stock} that we are building.
     */
    public StockBuilder withQuantity(String quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    /**
     * Sets the {@code Quantity} and lowQuantity of the {@code Stock} that we are building.
     */
    public StockBuilder withQuantity(String quantity, String lowQuantity) {
        this.quantity = new Quantity(quantity, lowQuantity);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Stock} that we are building.
     */
    public StockBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    public Stock build() {
        return new Stock(name, serialNumber, source, quantity, location);
    }

}
