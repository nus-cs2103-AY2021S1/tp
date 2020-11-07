package seedu.simplykitchen.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.commons.util.AppUtil.checkArgument;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a Food's Quantity in the Food inventory.
 * Guarantees: immutable; is valid as declared in {@link #isValidQuantity(String)}
 */
public class Quantity {
    public static final String QUANTITY_VALUE_CONSTRAINTS =
            "The value in the quantity field must be a positive number less than or equal to 100000.00. "
                    + "The value must have a maximum of two decimal places.";
    public static final String QUANTITY_UNIT_CONSTRAINTS =
            "The unit in the quantity field is optional. If provided, the unit should only contain alphabets."
            + "\nIf the unit is not provided, a default unit - \"unit\" - will be given.";
    public static final String MESSAGE_CONSTRAINTS =
            "The quantity field must contain a positive number followed by an optional unit.\n"
                    + "It should not be blank.";

    public static final String UNIT_VALIDATION_REGEX = "[a-zA-Z]*";
    public static final String VALUE_VALIDATION_REGEX = "[0-9]*([.][0-9][0-9]?)?";
    public static final double ZERO_VALUE = 0.00;
    public static final double MAX_VALUE = 100000.00;
    public static final String DEFAULT_UNIT = "unit";
    public final double value;
    public final String unit;

    /**
     * Constructs a {@code Quantity} from a {@code quantity} string.
     *
     * @param quantity a quantity string
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), generateErrorMessage(quantity));
        int indexOfSpace = quantity.indexOf(" ");
        String value = quantity;
        String unit = DEFAULT_UNIT;
        if (indexOfSpace > 0) {
            value = quantity.substring(0, indexOfSpace);
            unit = quantity.substring(indexOfSpace).trim();
        }
        this.value = Double.parseDouble(value);
        this.unit = unit;
    }

    /**
     * Constructs a {@code Quantity} from a double {@code value} and a {@code unit} string.
     *
     * @param value a double value
     * @param unit a unit string
     */
    public Quantity(double value, String unit) {
        String valueString = String.valueOf(value);
        String quantityString = valueString + " " + unit;
        checkArgument(isValidQuantity(quantityString), generateErrorMessage(quantityString));
        this.value = value;
        this.unit = unit;
    }

    /**
     * Returns if given parameters is a valid quantity string.
     */
    public static boolean isValidQuantity(String quantity) {
        if (quantity.length() == 0) {
            return false;
        }
        int indexOfSpace = quantity.indexOf(" ");
        String value = quantity;
        String unit = DEFAULT_UNIT;
        if (indexOfSpace > 0) {
            value = quantity.substring(0, indexOfSpace);
            unit = quantity.substring(indexOfSpace).trim();
        }

        // check validity of value and unit
        if (value.matches(VALUE_VALIDATION_REGEX) && unit.matches(UNIT_VALIDATION_REGEX)) {
            try {
                double doubleValue = Double.parseDouble(value);
                return doubleValue > ZERO_VALUE && doubleValue <= MAX_VALUE;
            } catch (NumberFormatException nfe) {
                return false;
            }
        }

        return false;
    }

    /**
     * Returns a new quantity value after adding the change in {@code amount} to the {@code oldQuantity} value.
     */
    public double updateQuantityValue(double amount) {
        BigDecimal bigDecimal = new BigDecimal(this.value).add(new BigDecimal(amount));
        BigDecimal finalBigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return finalBigDecimal.doubleValue();
    }

    /**
     * Returns a new {@code Quantity} with the {@code newValue} as its value and its existing unit as the unit.
     */
    public Quantity updateQuantity(double newValue) {
        return new Quantity(newValue, this.unit);
    }

    /**
     * Generates an error message based on why the quantity is invalid.
     *
     * @param quantity invalid quantity string
     * @return A string describing the error message.
     */
    public static String generateErrorMessage(String quantity) {
        if (quantity.length() == 0) {
            // empty quantity field
            return MESSAGE_CONSTRAINTS;
        }

        int indexOfSpace = quantity.indexOf(" ");
        String value = quantity;
        String unit = DEFAULT_UNIT;
        if (indexOfSpace > 0) {
            value = quantity.substring(0, indexOfSpace);
            unit = quantity.substring(indexOfSpace).trim();
        }

        if (!value.matches(VALUE_VALIDATION_REGEX)) {
            // invalid number of decimal places
            return QUANTITY_VALUE_CONSTRAINTS;
        }

        try {
            double doubleValue = Double.parseDouble(value);
            if (doubleValue <= ZERO_VALUE || doubleValue > MAX_VALUE) {
                // if the value is less than or equal to ZERO_VALUE or more than MAX_VALUE
                return QUANTITY_VALUE_CONSTRAINTS;
            }
        } catch (NumberFormatException nfe) {
            // invalid double value
            return QUANTITY_VALUE_CONSTRAINTS;
        }

        if (!unit.matches(UNIT_VALIDATION_REGEX)) {
            // unit does not match the validation regular expression
            return QUANTITY_UNIT_CONSTRAINTS;
        }

        return MESSAGE_CONSTRAINTS;
    }

    @Override
    public String toString() {
        // value must be a positive number less than or equal to MAX_VALUE
        assert(value > ZERO_VALUE && value <= MAX_VALUE);
        assert(unit.length() > 0); // unit should not be empty
        return String.format("%.2f %s", value, unit);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Quantity // instanceof handles nulls
                && value == (((Quantity) other).value)
                && unit.equals(((Quantity) other).unit)); // state check
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }
}
