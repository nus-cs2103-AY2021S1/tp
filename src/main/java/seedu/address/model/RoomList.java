package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.PriorityQueue;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.patient.Name;
import seedu.address.model.room.Room;
import seedu.address.model.room.UniqueRoomList;
import seedu.address.storage.JsonPatientRecordsStorage;

/**
 * Contains information regarding the Room information
 */
//@@author itssodium
public class RoomList implements ReadOnlyList<Room> {
    private static final Logger logger = LogsCenter.getLogger(JsonPatientRecordsStorage.class);

    private final UniqueRoomList rooms;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        rooms = new UniqueRoomList();
    }

    public RoomList() {}

    /**
     * Converts data from readOnlyRoomList to roomList
     */
    public RoomList(ReadOnlyList<Room> readOnlyRoomList) {
        this();
        resetData(readOnlyRoomList);
    }

    public boolean hasSpaceForRooms() {
        return rooms.hasSpaceForRooms();
    }

    public int getNumOfExcessOccupiedRooms() {
        return rooms.getNumOfExcessOccupiedRooms();
    }
    /**
     * Resets the existing data of this {@code RoomList} with {@code newData}.
     */
    public void resetData(ReadOnlyList<Room> readOnlyRoomList) {
        rooms.resetData(readOnlyRoomList);
    }
    /**
     * Returns Priority Queue of rooms
     */
    public PriorityQueue<Room> getRooms() {
        return rooms.getRooms();
    }

    /**
     * Returns number of rooms in hotel
     */
    public int getNumOfRooms() {
        return rooms.getNumOfRooms();
    }

    public void setInitNumOfRooms(int numOfRooms) {
        rooms.setPreferredNumOfRooms(numOfRooms);
    }

    public ObservableList<Room> getRoomObservableList() {
        return rooms.getRoomObservableList();
    }

    public void setRoom(Room room) {
        rooms.setRoom(room);
    }
    /**
     * Adds the number of the rooms in a hotel
     *
     * @param numOfRooms is the number of rooms to be added
     */
    public void addRooms(int numOfRooms) {
        rooms.addRooms(numOfRooms);
    }

    /**
     * Adds this room to the RoomList
     * @param room is added to RoomList
     */
    public void addRooms(Room room) {
        rooms.addRooms(room);
    }

    //@@author w-yeehong
    /**
     * Returns the room with the provided {@code roomNumber}.
     * An empty optional is returned if such a room is not found in the {@code RoomList}.
     *
     * @param roomNumber The room number of the room.
     * @return the optional-wrapped room if found, otherwise an empty optional
     */
    public Optional<Room> getRoomWithRoomNumber(int roomNumber) {
        assert (roomNumber > 0) : "Room number should be greater than 0.";
        for (Room room : getReadOnlyList()) {
            if (roomNumber == room.getRoomNumber()) {
                return Optional.of(room);
            }
        }
        return Optional.empty();
    }
    //@@author w-yeehong

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    @Override
    public ObservableList<Room> getReadOnlyList() {
        return rooms.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomList // instanceof handles nulls
                && rooms.equals(((RoomList) other).rooms));
    }

    /**
     * Returns true if the list contains an equivalent room as the given argument.
     */
    public boolean containsRoom(Room toCheck) {
        requireNonNull(toCheck);
        return rooms.containsRoom(toCheck);
    }

    //@@author chiamyunqing
    /**
     * Removes the patient with the given name {@code patientName} from the room.
     */
    public void removePatientFromRoom(Name patientName) {
        requireNonNull(patientName);
        rooms.clearRoom(patientName);
    }
    //@@author chiamyunqing

    //@author LeeMingDe
    /**
     * Replaces the room {@code target} in the list with {@code editedRoom}.
     * {@code target} must exist in the list.
     * The room identity of {@code editedRoom} must not be the same as another existing room in the list.
     *
     * @param target Room to be changed.
     * @param editedRoom Room that has been changed.
     */
    public void setSingleRoom(Room target, Room editedRoom) {
        rooms.setSingleRoom(target, editedRoom);
    }
    //@author LeeMingDe

    @Override
    public int hashCode() {
        return rooms.hashCode();
    }

    public void setPreferredNumOfRooms(int numOfRooms) {
        rooms.setPreferredNumOfRooms(numOfRooms);
    }

    public void setRooms(PriorityQueue<Room> rooms) {
        this.rooms.setRooms(rooms);
    }

}
