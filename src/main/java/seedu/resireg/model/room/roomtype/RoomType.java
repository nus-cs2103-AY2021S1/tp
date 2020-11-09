package seedu.resireg.model.room.roomtype;

import static seedu.resireg.commons.util.AppUtil.checkArgument;
import static seedu.resireg.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a room's type in ResiReg.
 * Guarantees: immutable; is valid as declared in {@link #isValidRoomType(String)}
 */
public class RoomType {


    public static final String MESSAGE_CONSTRAINTS =
            "A roomtype must be of the form \"CA\", \"CN\", \"NA\", or \"NN\".";

    public final String name;

    /**
     * Constructs a {@code RoomType}.
     *
     * @param typeAbbr A valid room type abbreviation.
     */
    public RoomType(String typeAbbr) {
        requireAllNonNull(typeAbbr);
        checkArgument(isValidRoomType(typeAbbr), MESSAGE_CONSTRAINTS);
        name = typeAbbr;
    }

    /**
     * Returns true if a given string is a valid room type abbreviation.
     */
    public static boolean isValidRoomType(String test) {
        List<RoomTypeEnum> roomTypes = Arrays.asList(RoomTypeEnum.values());
        return roomTypes.stream().anyMatch(roomType -> roomType.matchesRoomTypeAbbreviation(test));
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomType // instanceof handles nulls
                && name.equals(((RoomType) other).name));
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
