package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.Room.getRoomNumRange;
import static seedu.address.model.person.Room.isRoomWithinRange;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Room} is in the current floor.
 */
public class RoomMatchesNumberPredicate implements Predicate<Person> {
    public static final String MESSAGE_CONSTRAINTS =
            "Room number is invalid, it should be 2 digits long.\n"
            + "It should also be in the range " + getRoomNumRange() + ".";


    public static final String VALIDATION_REGEX = "\\d{2}";
    private static final int ROOM_NUMBER_START_INDEX = 1;
    private final String room;

    /**
     * Constructs a {@code RoomMatchesNumberPredicate}.
     *
     * @param room A valid room.
     */
    public RoomMatchesNumberPredicate(String room) {
        requireNonNull(room);
        checkArgument(isValidRoomNumber(room), MESSAGE_CONSTRAINTS);
        assert isValidRoomNumber(room) : "The string is a valid room number.";
        this.room = room;
    }

    /**
     * Returns true if a given string is a valid room number.
     */
    public static boolean isValidRoomNumber(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        int roomNo = Integer.parseInt(test);
        return isRoomWithinRange(roomNo);
    }

    @Override
    public boolean test(Person person) {
        return person.getRoom().value.substring(ROOM_NUMBER_START_INDEX).equals(room);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomMatchesNumberPredicate // instanceof handles nulls
                && room.equals(((RoomMatchesNumberPredicate) other).room)); // state check
    }

}
