package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Room number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRoom(String)}
 */
public class Room {
    public static final String MESSAGE_CONSTRAINTS =
            "Room number should be 3 digits long, "
                    + "the 1st digit represents the floor, the 2nd and 3rd refers to the room no.";
    public static final String VALIDATION_REGEX = "\\d{3}";
    private static int minFloorNumber = 1;
    private static int minRoomNumber = 1;
    private static int maxFloorNumber = 4;
    private static int maxRoomNumber = 20;
    public final String value;

    /**
     * Constructs a {@code Room}.
     *
     * @param room A valid Room number.
     */
    public Room(String room) {
        requireNonNull(room);
        checkArgument(isValidRoom(room), getMessageConstraints());
        value = room;
    }

    /**
     * Returns true if a given string is a valid Room number.
     */
    public static boolean isValidRoom(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        int room = Integer.parseInt(test);
        int roomNo = room % 100;
        int floorNo = Math.floorDiv(room, 100);
        return isFloorWithinRange(floorNo) && isRoomWithinRange(roomNo);
    }

    public static boolean isRoomWithinRange(int roomNo) {
        return (roomNo <= maxRoomNumber) && (roomNo >= minRoomNumber);
    }

    public static boolean isFloorWithinRange(int floorNo) {
        return (floorNo <= maxFloorNumber) && (floorNo >= minFloorNumber);
    }

    public static String getFloorRange() {
        return "Floors : " + minFloorNumber + " to " + maxFloorNumber;
    }

    public static String getFloorNumRange() {
        return minFloorNumber + " - " + maxFloorNumber;
    }

    public static String getRoomRange() {
        return "Rooms : " + minFloorNumber + String.format("%02d", minRoomNumber) + " to "
                + maxFloorNumber + String.format("%02d", maxRoomNumber);
    }

    public static String getRoomNumRange() {
        return String.format("%02d", minRoomNumber) + " - " + String.format("%02d", maxRoomNumber);
    }

    public static String getMessageConstraints() {
        return MESSAGE_CONSTRAINTS + "\nFloors : " + minFloorNumber + " to " + maxFloorNumber
                + "\nRooms: " + minRoomNumber + " - " + maxRoomNumber;
    }

    public static void setRoomPref(int minRoom, int maxRoom, int minFloor, int maxFloor) {
        minRoomNumber = minRoom;
        maxRoomNumber = maxRoom;
        minFloorNumber = minFloor;
        maxFloorNumber = maxFloor;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Room // instanceof handles nulls
                && value.equals(((Room) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
