package seedu.stock.model.stock;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class SerialNumberSet {

    private Source source;
    private AccQuantity accQuantity;

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
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameSerialNumberSetSource(SerialNumberSet otherSerialNumberSet) {
        if (otherSerialNumberSet == this) {
            return true;
        }

        return otherSerialNumberSet != null
                && otherSerialNumberSet.getSource().equals(getSource());
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameSerialNumberSet(SerialNumberSet otherSerialNumberSet) {
        if (otherSerialNumberSet == this) {
            return true;
        }

        return otherSerialNumberSet != null
                && otherSerialNumberSet.getSource().equals(getSource())
                && otherSerialNumberSet.getAccQuantity().equals(getAccQuantity());
    }

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
