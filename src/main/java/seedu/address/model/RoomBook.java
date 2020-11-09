package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.room.Room;
import seedu.address.model.room.UniqueRoomList;


public class RoomBook implements ReadOnlyRoomBook {

    private final UniqueRoomList rooms;

    {
        rooms = new UniqueRoomList();
    }

    public RoomBook() {}

    /**
     * Creates an RoomBook using the rooms in the {@code toBeCopied}
     */
    public RoomBook(ReadOnlyRoomBook tobeCopied) {
        this();
        resetData(tobeCopied);
    }

    /// list level operations
    /**
     * Returns a {@code ObservableList} that gets all available rooms.
     */
    public ObservableList<Integer> getAvailableRooms(ObservableList<Integer> unavailableRooms) {
        return rooms.getComplementRooms(unavailableRooms);
    }

    /**
     * Adds a room to the room book.
     * The room must not already exist in the room book.
     */
    public void addRoom(Room r) {
        rooms.add(r);
    }


    /**
     * Replaces the contents of the room list with {@code rooms}.
     * {@code rooms} must not contain duplicate rooms.
     */
    public void setRooms(List<Room> rooms) {
        this.rooms.setRooms(rooms);
    }

    /**
     * Returns a {@code String} that lists in rooms according to room types.
     */
    public String displayRooms(ObservableList<Integer> input) {
        return rooms.getAllRooms(input);
    }

    /**
     * Returns a {@code String} that lists only Single Rooms.
     */
    public String getSingleRooms(ObservableList<Integer> input) {
        return rooms.getSingleRooms(input);
    }

    /**
     * Returns a {@code String} that lists only Double Rooms.
     */
    public String getDoubleRooms(ObservableList<Integer> input) {
        return rooms.getDoubleRooms(input);
    }

    /**
     * Returns a {@code String} that lists only Suite Rooms.
     */
    public String getSuiteRooms(ObservableList<Integer> input) {
        return rooms.getSuiteRooms(input);
    }

    /**
     * Resets the existing data of this {@code RoomBook} with {@code newData}.
     */
    public void resetData(ReadOnlyRoomBook newData) {
        requireNonNull(newData);

        setRooms(newData.getRoomList());
    }

    /// room level operations
    /**
     * Returns true if a room with the same roomId exists in the address book.
     */
    public boolean hasRoom(Integer roomId) {
        return rooms.contains(roomId);
    }

    /**
     * Returns a room to the room book.
     */
    public Room getRoom(int roomId) {
        return rooms.getRoom(roomId);
    }

    /// util methods
    @Override
    public ObservableList<Room> getRoomList() {
        return rooms.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomBook // instanceof handles nulls
                && rooms.equals(((RoomBook) other).rooms));
    }
}
