package seedu.resireg.model.room;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.commons.util.AppUtil.checkArgument;

/**
 * Represents a room's number in ResiReg.
 * Guarantees: immutable; is valid as declared in {@link #isValidRoomNumber(String)}
 */
public class RoomNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "Room numbers should only contain numbers, cannot start with 0 "
                    + "and it should be at most 3 digits long";

    public static final String VALIDATION_REGEX = "^[1-9][0-9][0-9]$";
    public final String value;

    /**
     * Constructs a {@code Number}.
     *
     * @param number A valid room number.
     */
    public RoomNumber(String number) {
        requireNonNull(number);
        checkArgument(isValidRoomNumber(number), MESSAGE_CONSTRAINTS);
        value = number;
    }

    /**
     * Returns true if a given string is a valid number.
     */
    public static boolean isValidRoomNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.resireg.model.room.RoomNumber // instanceof handles nulls
                && value.equals(((seedu.resireg.model.room.RoomNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
