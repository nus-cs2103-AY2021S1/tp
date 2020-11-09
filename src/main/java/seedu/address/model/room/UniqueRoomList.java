package seedu.address.model.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.room.exceptions.DuplicateRoomException;

public class UniqueRoomList implements Iterable<Room> {

    public static final Room EMPTY_ROOM = new Single(-1);
    private final ObservableList<Room> internalRoomList = FXCollections.observableArrayList();
    private final ObservableList<Room> internalUnmodifiableRoomList =
            FXCollections.unmodifiableObservableList(internalRoomList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(int roomId) {
        return internalRoomList.stream().anyMatch(n -> n.getRoomID() == (roomId));
    }

    /**
     * Adds a Room to the list.
     * The room must not already exist in the list.
     */
    public void add(Room toAdd) {
        requireNonNull(toAdd);
        if (internalRoomList.contains(toAdd)) {
            throw new DuplicateRoomException();
        }
        internalRoomList.add(toAdd);
    }

    /**
     * Adds a Room to the list.
     * The room must not already exist in the list.
     */
    public Room getRoom(int roomId) {
        assert roomId >= 2103 && roomId < 2133;
        Room ret = EMPTY_ROOM;
        for (int k = 0; k < internalRoomList.size(); k++) {
            Room curr = internalRoomList.get(k); // get the roomID
            if (curr.getRoomID() == roomId) {
                ret = curr;
            }
        }
        return ret;
    }

    /**
     * Returns an {@code ObservableList} of roomIDs that are not in the input
     * @param input {@code ObservableList} of roomIDs
     * @return {@code ObservableList} of rooms
     */
    public ObservableList<Integer> getComplementRooms(ObservableList<Integer> input) {
        assert !internalRoomList.isEmpty();
        ObservableList<Integer> ret = FXCollections.observableArrayList();
        for (int k = 0; k < internalRoomList.size(); k++) {
            int curr = internalRoomList.get(k).getRoomID(); // get the roomID
            if (!input.contains(curr)) {
                ret.add(curr);
            }
        }
        return ret;
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setRooms(List<Room> rooms) {
        requireAllNonNull(rooms);
        if (!roomsAreUnique(rooms)) {
            throw new DuplicateRoomException();
        }

        internalRoomList.setAll(rooms);
    }

    public void setRooms(UniqueRoomList replacement) {
        requireNonNull(replacement);
        internalRoomList.setAll(replacement.internalRoomList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Room> asUnmodifiableObservableList() {
        return internalUnmodifiableRoomList;
    }

    @Override
    public Iterator<Room> iterator() {
        return internalRoomList.iterator();
    }

    /**
     * Returns true if {@code persons} contains only unique rooms.
     */
    private boolean roomsAreUnique(List<Room> rooms) {
        for (int i = 0; i < rooms.size() - 1; i++) {
            for (int j = i + 1; j < rooms.size(); j++) {
                if (rooms.get(i).equals(rooms.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a {@code String} that lists in rooms according to room types.
     */
    public String getAllRooms(ObservableList<Integer> rooms) {
        ArrayList<Integer> singleRooms = new ArrayList<>();
        ArrayList<Integer> doubleRooms = new ArrayList<>();
        ArrayList<Integer> suiteRooms = new ArrayList<>();

        for (int i : rooms) {
            if (i < 2103) {
                // do nothing
            } else if (i < 2113) {
                singleRooms.add(i);
            } else if (i < 2123) {
                doubleRooms.add(i);
            } else if (i < 2133) {
                suiteRooms.add(i);
            } else {
                // do nothing
            }
        }

        String singleRoomsData = "Single Rooms: " + singleRooms.size() + " "
                + Arrays.toString(singleRooms.toArray()) + "\n";
        String doubleRoomsData = "Double Rooms: " + doubleRooms.size() + " "
                + Arrays.toString(doubleRooms.toArray()) + "\n";
        String suiteRoomsData = "Suite Rooms: " + suiteRooms.size() + " "
                + Arrays.toString(suiteRooms.toArray()) + "\n";

        return singleRoomsData + doubleRoomsData + suiteRoomsData;
    }

    /**
     * Returns a {@code String} that displays only the single rooms available in the {@code rooms}
     */
    public String getSingleRooms(ObservableList<Integer> rooms) {
        ArrayList<Integer> singleRooms = new ArrayList<>();
        for (int i : rooms) {
            if (i < 2103) {
                // do nothing
            } else if (i < 2113) {
                singleRooms.add(i);
            } else {
                break;
            }
        }
        return "Single Rooms: " + singleRooms.size() + " " + Arrays.toString(singleRooms.toArray()) + "\n";
    }

    /**
     * Returns a {@code String} that displays only the double rooms available in the {@code rooms}
     */
    public String getDoubleRooms(ObservableList<Integer> rooms) {
        ArrayList<Integer> doubleRooms = new ArrayList<>();
        for (int i : rooms) {
            if (i < 2113) {
                // do nothing
            } else if (i < 2123) {
                doubleRooms.add(i);
            } else {
                break;
            }
        }
        return "Double Rooms: " + doubleRooms.size() + " " + Arrays.toString(doubleRooms.toArray()) + "\n";
    }

    /**
     * Returns a {@code String} that displays only the suite rooms available in the {@code rooms}
     */
    public String getSuiteRooms(ObservableList<Integer> rooms) {
        ArrayList<Integer> suiteRooms = new ArrayList<>();
        for (int i : rooms) {
            if (i < 2123) {
                // do nothing
            } else if (i < 2133) {
                suiteRooms.add(i);
            } else {
                break;
            }
        }
        return "Suite Rooms: " + suiteRooms.size() + " " + Arrays.toString(suiteRooms.toArray()) + "\n";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueRoomList // instanceof handles nulls
                && internalRoomList.equals(((UniqueRoomList) other).internalRoomList));
    }
}
