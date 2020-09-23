package seedu.stock.model.stock;

import static java.util.Objects.requireNonNull;

/**
 * Represents the serial number in the stock book.
 * Guarantees: immutable;
 */
public class SerialNumber {
    public static final String MESSAGE_CONSTRAINTS =
            "Serial numbers should only contain numbers, and it should be at least 2 digits long";
    public static final String VALIDATION_REGEX = "\\d{2,}";

    public final String serialNumber;


    /**
     * Constructs an {@code SerialNumber}.
     * SerialNumber can only be created by calling {@link SerialNumber#generateSerialNumber()}
     *
     * @param serialNumber A valid serial number.
     */
    public SerialNumber(String serialNumber) {
        requireNonNull(serialNumber);
        this.serialNumber = serialNumber;
    }

    /**
     * Returns true if a given string is a valid serial number.
     */
    public static boolean isValidSerialNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Generates the serial number of the product.
     * params to be filled in later
     *
     * @return SerialNumber of the object.
     */
    public static SerialNumber generateSerialNumber() {
        return new SerialNumber("12");
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
