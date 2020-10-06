package seedu.stock.model.stock;

import static seedu.stock.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;


/**
 * Represents a Stock in the stock book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Stock {

    // Identity fields
    private final Name name;
    private SerialNumber serialNumber;
    private final Source source;
    private final Quantity quantity;
    private final Location location;

    /**
     * Every field must be present and not null.
     */
    public Stock(Name name, SerialNumber serialNumber, Source source, Quantity quantity, Location location) {
        requireAllNonNull(name, serialNumber, source, quantity, location);
        this.name = name;
        this.serialNumber = serialNumber;
        this.source = source;
        this.quantity = quantity;
        this.location = location;
    }

    public Name getName() {
        return name;
    }

    public SerialNumber getSerialNumber() {
        return serialNumber;
    }

    public Source getSource() {
        return source;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Location getLocation() {
        return location;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = new SerialNumber(serialNumber);
    }

    /**
     * Returns true if both stocks of the same name, serialnumber and source.
     * This defines a weaker notion of equality between two stocks.
     */
    public boolean isSameStock(Stock otherStock) {
        if (otherStock == this) {
            return true;
        }

        return otherStock != null
                && otherStock.getName().equals(getName())
                && otherStock.getSerialNumber().equals(getSerialNumber())
                && otherStock.getSource().equals(getSource());
    }

    /**
     * Returns true if both stocks have the same identity and data fields.
     * This defines a stronger notion of equality between two stocks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Stock)) {
            return false;
        }

        Stock otherStock = (Stock) other;
        return otherStock.getName().equals(getName())
                && otherStock.getSerialNumber().equals(getSerialNumber())
                && otherStock.getSource().equals(getSource())
                && otherStock.getQuantity().equals(getQuantity())
                && otherStock.getLocation().equals(getLocation());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, serialNumber, quantity, source, location);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" SerialNumber: ")
                .append(getSerialNumber())
                .append(" Source: ")
                .append(getSource())
                .append(" Quantity: ")
                .append(getQuantity())
                .append(" Location: ")
                .append(getLocation());
        return builder.toString();
    }

}
