package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.Room.getFloorNumRange;
import static seedu.address.model.person.Room.isFloorWithinRange;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Room} is in the current floor.
 */
public class RoomInFloorPredicate implements Predicate<Person> {
    public static final String MESSAGE_CONSTRAINTS =
            "Floor should have one number inside.\n"
            + "It should also be within " + getFloorNumRange() + ".";

    public static final String VALIDATION_REGEX = "\\d";
    private final String floor;

    /**
     * Constructs a {@code RoomInFloorPredicate}.
     *
     * @param floor A valid floor.
     */
    public RoomInFloorPredicate(String floor) {
        requireNonNull(floor);
        checkArgument(isValidFloorNumber(floor), MESSAGE_CONSTRAINTS);
        this.floor = floor;
    }

    /**
     * Returns true if a given string is a valid floor number.
     */
    public static boolean isValidFloorNumber(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        int floorNo = Integer.parseInt(test);
        return isFloorWithinRange(floorNo);
    }

    @Override
    public boolean test(Person person) {
        return person.getRoom().value.startsWith(floor);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomInFloorPredicate // instanceof handles nulls
                && floor.equals(((RoomInFloorPredicate) other).floor)); // state check
    }

}
