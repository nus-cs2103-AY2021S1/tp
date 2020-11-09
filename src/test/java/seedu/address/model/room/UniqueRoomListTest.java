package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.room.exceptions.DuplicateRoomException;

public class UniqueRoomListTest {

    private final UniqueRoomList uniqueRoomList = new UniqueRoomList();

    @Test
    public void contains_roomNotInList_returnsFalse() {
        assertFalse(uniqueRoomList.contains(DEFAULT_ROOMID));
    }

    @Test public void contains_roomInList_returnsTrue() {
        uniqueRoomList.add(DEFAULT_ROOM);
        assertTrue(uniqueRoomList.contains(DEFAULT_ROOMID));
    }

    @Test
    public void add_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.add(null));
    }

    @Test
    public void add_duplicateRoom_throwsDuplicatePersonException() {
        uniqueRoomList.add(DEFAULT_ROOM);
        assertThrows(DuplicateRoomException.class, () -> uniqueRoomList.add(DEFAULT_ROOM));
    }

    @Test
    public void setRoom_nullUniqueRoomList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.setRooms((List<Room>) null));
    }

    @Test
    public void setRooms_uniqueRoomList_replacesOwnListWithProvidedUniqueRoomList() {
        uniqueRoomList.add(SINGLE_1);
        UniqueRoomList expectedUniqueRoomList = new UniqueRoomList();
        expectedUniqueRoomList.add(SINGLE_2);
        uniqueRoomList.setRooms(expectedUniqueRoomList);
        assertEquals(expectedUniqueRoomList, uniqueRoomList);
    }

    @Test
    public void setRooms_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.setRooms((List<Room>) null));
    }

    @Test
    public void setRooms_list_replacesOwnListWithProvidedList() {
        uniqueRoomList.add(SINGLE_1);
        List<Room> roomList = Collections.singletonList(SINGLE_2);
        uniqueRoomList.setRooms(roomList);
        UniqueRoomList expectedUniqueBookingList = new UniqueRoomList();
        expectedUniqueBookingList.add(SINGLE_2);
        assertEquals(expectedUniqueBookingList, uniqueRoomList);
    }

    @Test
    public void setRooms_listWithDuplicateRooms_throwsDuplicateRoomException() {
        List<Room> listWithDuplicateRooms = Arrays.asList(SINGLE_1, SINGLE_1);
        assertThrows(DuplicateRoomException.class, () -> uniqueRoomList.setRooms(listWithDuplicateRooms));
    }
    @Test
    public void get_roomNotInList_returnsEmptyRoom() {
        assertEquals(UniqueRoomList.EMPTY_ROOM, uniqueRoomList.getRoom(DEFAULT_ROOMID));
    }

    @Test
    public void get_roomInList_returnCorrectRoom() {
        uniqueRoomList.add(DEFAULT_ROOM);
        assertEquals(DEFAULT_ROOM, uniqueRoomList.getRoom(2103));
    }

    @Test
    public void getComplementRooms_returnsCorrectList() {
        List<Room> rooms = FXCollections.observableArrayList(SINGLE_1, SINGLE_2, DOUBLE_1, DOUBLE_2, SUITE_1, SUITE_2);
        uniqueRoomList.setRooms(rooms);
        ObservableList<Integer> input = FXCollections.observableArrayList(SINGLE_1.getRoomID(),
                                                                            DOUBLE_1.getRoomID(),
                                                                            SUITE_1.getRoomID());
        List<Integer> output = uniqueRoomList.getComplementRooms(input);
        assertTrue(output.contains(SINGLE_2.getRoomID())
                            && output.contains(DOUBLE_2.getRoomID())
                            && output.contains(SUITE_2.getRoomID())
                            && !output.contains(SINGLE_1.getRoomID())
                            && !output.contains(DOUBLE_1.getRoomID())
                            && !output.contains(SUITE_1.getRoomID()));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
            uniqueRoomList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void getAllRooms_returnsCorrectOutput() {
        ObservableList<Integer> rooms = FXCollections.observableArrayList(SINGLE_1.getRoomID(),
                SINGLE_2.getRoomID(), DOUBLE_1.getRoomID(), DOUBLE_2.getRoomID(),
                SUITE_1.getRoomID(), SUITE_2.getRoomID());
        String actualOutput = uniqueRoomList.getAllRooms(rooms);
        Integer[] singleRooms = {2104, 2105};
        Integer[] doubleRooms = {2113, 2114};
        Integer[] suiteRooms = {2123, 2124};

        String expectedOutput = "Single Rooms: " + singleRooms.length + " " + Arrays.toString(singleRooms) + "\n"
                + "Double Rooms: " + doubleRooms.length + " " + Arrays.toString(doubleRooms) + "\n"
                + "Suite Rooms: " + suiteRooms.length + " " + Arrays.toString(suiteRooms) + "\n";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void getSingleRooms_returnsCorrectOutput() {
        ObservableList<Integer> rooms = FXCollections.observableArrayList(SINGLE_1.getRoomID(),
                SINGLE_2.getRoomID(), DOUBLE_1.getRoomID(), DOUBLE_2.getRoomID(),
                SUITE_1.getRoomID(), SUITE_2.getRoomID());
        String actualOutput = uniqueRoomList.getSingleRooms(rooms);
        Integer[] singleRooms = {2104, 2105};
        String expectedOutput = "Single Rooms: " + singleRooms.length + " " + Arrays.toString(singleRooms) + "\n";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void getDoubleRooms_returnsCorrectOutput() {
        ObservableList<Integer> rooms = FXCollections.observableArrayList(SINGLE_1.getRoomID(),
                SINGLE_2.getRoomID(), DOUBLE_1.getRoomID(), DOUBLE_2.getRoomID(),
                SUITE_1.getRoomID(), SUITE_2.getRoomID());
        String actualOutput = uniqueRoomList.getDoubleRooms(rooms);
        Integer[] doubleRooms = {2113, 2114};

        String expectedOutput = "Double Rooms: " + doubleRooms.length + " " + Arrays.toString(doubleRooms) + "\n";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void getSuiteRooms_returnsCorrectOutput() {
        ObservableList<Integer> rooms = FXCollections.observableArrayList(SINGLE_1.getRoomID(),
                SINGLE_2.getRoomID(), DOUBLE_1.getRoomID(), DOUBLE_2.getRoomID(),
                SUITE_1.getRoomID(), SUITE_2.getRoomID());
        String actualOutput = uniqueRoomList.getSuiteRooms(rooms);
        Integer[] suiteRooms = {2123, 2124};

        String expectedOutput = "Suite Rooms: " + suiteRooms.length + " " + Arrays.toString(suiteRooms) + "\n";

        assertEquals(expectedOutput, actualOutput);
    }
}
