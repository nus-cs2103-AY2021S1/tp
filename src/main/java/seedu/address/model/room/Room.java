package seedu.address.model.room;

import java.util.Objects;

import seedu.address.model.patient.Patient;
import seedu.address.model.tasks.Task;

/**
 * Represents Room in the app
 */
public class Room implements Comparable<Room> {

    public static final String MESSAGE_OCCUPANCY_CONSTRAINTS =
            "Occupancy should only be either true or false and not other words";

    private int roomNumber;
    private boolean isOccupied;
    private Patient patient;
    private Task task;

    /**
     * Creates room object where roomNumber and isOccupied values are values given by user
     */
    public Room(int roomNumber, boolean isOccupied) {
        this.roomNumber = roomNumber;
        this.isOccupied = isOccupied;
        this.patient = null;
        this.task = null;
    }

    /**
     * Creates room object where isOccupied is always false
     */
    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isOccupied = false;
        this.patient = null;
        this.task = null;
    }
    public int getRoomNumber() {
        return roomNumber;
    }

    public Patient getPatient() {
        return patient;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    /**
     * Returns true if both rooms of the same number have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two rooms.
     */
    public boolean isSameRoom(Room otherRoom) {
        if (otherRoom == this) {
            return true;
        }

        return otherRoom != null
                && (Integer.valueOf(otherRoom.getRoomNumber()).equals(getRoomNumber()))
                && ((Boolean.valueOf(otherRoom.isOccupied)).equals(isOccupied)
                //TODO Update this once patient is integrated into rooms.
                /* || otherRoom.getPatient().equals(getPatient())*/);
    }

    /**
     * Returns true if both rooms have the same identity and data fields.
     * This defines a stronger notion of equality between two rooms.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return roomNumber == room.roomNumber
                && isOccupied == room.isOccupied;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, isOccupied);
    }

    @Override
    public int compareTo(Room room) {
        if (room.isOccupied == this.isOccupied) {
            if (room.roomNumber < this.roomNumber) {
                return 1;
            } else {
                return -1;
            }
        } else {
            if (room.isOccupied) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    /**
     * Returns true if a given string is a valid boolean value.
     */
    public static boolean isValidOccupancy(String test) {
        return test.trim().toLowerCase().equals("true") || test.trim().toLowerCase().equals("false");
    }

    public String toString() {
        return "Room number " + this.roomNumber;
    }
}
