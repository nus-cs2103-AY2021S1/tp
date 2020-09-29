package seedu.stock.model.stock;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class SerialNumberSet {

    private Source source;
    private AccQuantity accQuantity;

    /**
     * Constructor for a serialNumberSet.
     *
     * @param source The source company name.
     * @param accQuantity The accumulated quantity of stocks the source company has in relation to
     *     the local warehouse.
     */
    public SerialNumberSet(Source source, AccQuantity accQuantity) {
        requireNonNull(source);
        requireNonNull(accQuantity);
        this.source = source;
        this.accQuantity = accQuantity;
    }

    public Source getSource() {
        return this.source;
    }

    public AccQuantity getAccQuantity() {
        return this.accQuantity;
    }

    /**
     * Returns true if both serialNumberSet has the same source company.
     */
    public boolean isSameSerialNumberSetSource(SerialNumberSet otherSerialNumberSet) {
        if (otherSerialNumberSet == this) {
            return true;
        }

        return otherSerialNumberSet != null
                && otherSerialNumberSet.getSource().equals(getSource());
    }

    /**
     * Returns true if both serialNumberSet is the same.
     */
    public boolean isSameSerialNumberSet(SerialNumberSet otherSerialNumberSet) {
        if (otherSerialNumberSet == this) {
            return true;
        }

        return otherSerialNumberSet != null
                && otherSerialNumberSet.getSource().equals(getSource())
                && otherSerialNumberSet.getAccQuantity().equals(getAccQuantity());
    }

    /**
     * Gets a new SerialNumberSet with an increment quantity of 1.
     *
     * @return The new SerialNumberSet.
     */
    public SerialNumberSet getNewIncrementedSerialNumberSet() {
        Source newSource = this.source;
        AccQuantity newAccQuantity = this.accQuantity.getIncrementedAccQuantity();
        return new SerialNumberSet(newSource, newAccQuantity);
    }

    /**
     * Returns true if both serial number set have the same identity and data fields.
     * This defines a stronger notion of equality between two serial number set.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SerialNumberSet)) {
            return false;
        }

        SerialNumberSet otherSerialNumberSet = (SerialNumberSet) other;
        return otherSerialNumberSet.getSource().equals(getSource())
                && otherSerialNumberSet.getAccQuantity().equals(getAccQuantity());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(source, accQuantity);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Source: ")
                .append(getSource())
                .append(" AccQuantity: ")
                .append(getAccQuantity());
        return builder.toString();
    }


}
