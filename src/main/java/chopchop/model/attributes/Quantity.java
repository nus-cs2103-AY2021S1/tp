package chopchop.model.attributes;

import static chopchop.commons.util.AppUtil.checkArgument;

public class Quantity {

    public static final String MESSAGE_CONSTRAINTS =
        "The quantity should be at least 1";

    private final int num;

    /**
     * Constructs a {@code Quantity}.
     *
     * @param num A valid integer of arbitrary unit.
     */
    public Quantity(int num) {
        assert num > 0;
        checkArgument(isValidNum(num), MESSAGE_CONSTRAINTS);
        this.num = num;
    }

    /**
     * Returns true if a given int is a valid number.
     */
    public static boolean isValidNum(int num) {
        return num > 0;
    }

}
