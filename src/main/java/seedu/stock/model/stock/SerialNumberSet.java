package seedu.stock.model.stock;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class SerialNumberSet {

    private Source source;
    private AccumulatedQuantity accumulatedQuantity;

    /**
     * Constructor for a serialNumberSet.
     *
     * @param source The source company name.
     * @param accumulatedQuantity The accumulated quantity of stocks the source company has in relation to
     * the local warehouse.
     */
    public SerialNumberSet(Source source, AccumulatedQuantity accumulatedQuantity) {
        requireNonNull(source);
        requireNonNull(accumulatedQuantity);
        this.source = source;
        this.accumulatedQuantity = accumulatedQuantity;
    }

    public Source getSource() {
        return this.source;
    }

    public AccumulatedQuantity getAccumulatedQuantity() {
        return this.accumulatedQuantity;
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
                && otherSerialNumberSet.getAccumulatedQuantity().equals(getAccumulatedQuantity());
    }

    /**
     * Gets a new SerialNumberSet with an increment quantity of 1.
     *
     * @return The new SerialNumberSet.
     */
    public SerialNumberSet getNewIncrementedSerialNumberSet() {
        Source newSource = this.source;
        AccumulatedQuantity newAccQuantity = this.accumulatedQuantity.getIncrementedAccumulatedQuantity();
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
                && otherSerialNumberSet.getAccumulatedQuantity().equals(getAccumulatedQuantity());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(source, accumulatedQuantity);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Source: ")
                .append(getSource())
                .append(" AccumulatedQuantity: ")
                .append(getAccumulatedQuantity());
        return builder.toString();
    }


}
