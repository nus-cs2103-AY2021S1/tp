package seedu.resireg.model.room;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.commons.util.AppUtil.checkArgument;

/**
 * Represents a room's floor number in ResiReg.
 * Guarantees: immutable; is valid as declared in {@link #isValidFloor(String)}
 */
public class Floor {

    public static final String MESSAGE_CONSTRAINTS =
            "Floor numbers should only contain numbers, cannot start with 0 and should be at most 2 digits long";

    public static final String VALIDATION_REGEX = "^[1-9][0-9]?$";
    public final String value;

    /**
     * Constructs a {@code Floor}.
     *
     * @param floor A valid floor number.
     */
    public Floor(String floor) {
        requireNonNull(floor);
        checkArgument(isValidFloor(floor), MESSAGE_CONSTRAINTS);
        value = floor;
    }

    /**
     * Returns true if a given string is a valid floor number.
     */
    public static boolean isValidFloor(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.resireg.model.room.Floor // instanceof handles nulls
                && value.equals(((seedu.resireg.model.room.Floor) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
