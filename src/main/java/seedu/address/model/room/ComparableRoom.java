package seedu.address.model.room;

import java.util.Comparator;

public class ComparableRoom implements Comparator<Room> {
    @Override
    public int compare(Room room1, Room room2) {
        if (room1.isOccupied() == room2.isOccupied()) {
            if (room1.getRoomNumber() < room2.getRoomNumber()) {
                return -1;
            } else {
                return 1;
            }
        } else if (room1.isOccupied()) {
            return 1;
        } else {
            return -1;
        }
    }
}
