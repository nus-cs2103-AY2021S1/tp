package seedu.stock.model.stock;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.util.AppUtil.checkArgument;

/**
 * Represents the serial number in the serialNumberSets Book.
 * Guarantees: immutable;
 */
public class SerialNumber {
    public static final String MESSAGE_CONSTRAINTS =
            "Serial numbers should not be empty and must be at least of length 2.";
    public static final String VALIDATION_REGEX = ".*\\d.*";
    public static final String DEFAULT_SERIAL_NUMBER = "00";
    private final String serialNumber;


    /**
     * Constructs an {@code SerialNumber}.
     * SerialNumber can only be created by calling {@link SerialNumber#generateDefaultSerialNumber()}
     *
     * @param serialNumber A valid serial number.
     */
    public SerialNumber(String serialNumber) {
        requireNonNull(serialNumber);
        checkArgument(isValidSerialNumber(serialNumber), MESSAGE_CONSTRAINTS);
        this.serialNumber = serialNumber;
    }

    /**
     * Returns true if a given string is a valid serial number.
     */
    public static boolean isValidSerialNumber(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() > 1;
    }

    public String getSerialNumberAsString() {
        return serialNumber;
    }

    /**
     * Generates the default serial number of the product.
     *
     * @return SerialNumber of the object.
     */
    public static SerialNumber generateDefaultSerialNumber() {
        return new SerialNumber(DEFAULT_SERIAL_NUMBER);
    }

    @Override
    public String toString() {
        return serialNumber;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SerialNumber // instanceof handles nulls
                && serialNumber.equals(((SerialNumber) other).serialNumber)); // state check
    }

    @Override
    public int hashCode() {
        return serialNumber.hashCode();
    }
}
