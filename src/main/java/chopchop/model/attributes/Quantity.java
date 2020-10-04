package chopchop.model.attributes;

import static chopchop.commons.util.AppUtil.checkArgument;

public class Quantity {

    public static final String MESSAGE_CONSTRAINTS =
        "The quantity should be at least 1";

    public final int value;

    /**
     * Constructs a {@code Quantity}.
     *
     * @param value A valid integer of arbitrary unit.
     */
    public Quantity(int value) {
        assert value > 0;
        checkArgument(isValidNum(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given int is a valid number.
     */
    public static boolean isValidNum(int value) {
        return value > 0;
    }

}
