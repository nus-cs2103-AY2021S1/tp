package seedu.resireg.model.semester;

import static seedu.resireg.commons.util.AppUtil.checkArgument;

/**
 * Represents a room's rate for a semester in ResiReg.
 * Guarantees: immutable; is valid as declared in {@link #isValidRoomRate(int)}
 */
public class RoomRate {

    public static final String MESSAGE_CONSTRAINTS = "Room rate should not be a negative number";
    public final int value;

    /**
     * Constructs a {@code RoomRate}.
     *
     * @param value A valid room rate.
     */
    public RoomRate(int value) {
        checkArgument(isValidRoomRate(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    public static boolean isValidRoomRate(int value) {
        return value > 0;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomRate // instanceof handles nulls
                && value == ((RoomRate) other).value); // state check
    }

    @Override
    public int hashCode() {
        return value;
    }

}
