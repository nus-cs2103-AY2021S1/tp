package seedu.address.model;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.room.Room;
import seedu.address.model.room.UniqueRoomList;
import seedu.address.model.room.exceptions.DuplicateRoomException;
import seedu.address.testutil.RoomBuilder;

public class RoomBookTest {

    private final RoomBook roomBook = new RoomBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), roomBook.getRoomList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyRoomBook_replacesData() {
        RoomBook newData = getTypicalRoomBook();
        roomBook.resetData(newData);
        assertEquals(newData, roomBook);
    }

    @Test
    public void resetData_withDuplicateRooms_throwsDuplicateRoomException() {
        Room editedSingle1 = new RoomBuilder(DOUBLE_1)
                .withPrice(SINGLE_1.getPrice())
                .withRoomID(SINGLE_1.getRoomID())
                .build();
        List<Room> newRooms = Arrays.asList(SINGLE_1, editedSingle1);

        RoomBookTest.RoomBookStub newData = new RoomBookTest.RoomBookStub(newRooms);

        assertThrows(DuplicateRoomException.class, () -> roomBook.resetData(newData));
    }

    @Test
    public void hasRoom_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomBook.hasRoom(null));
    }

    @Test
    public void hasRoom_roomNotInRoomBook_returnsFalse() {
        assertFalse(roomBook.hasRoom(SINGLE_1.getRoomID()));
    }

    @Test
    public void hasRoom_roomInRoomBook_returnsTrue() {
        roomBook.addRoom(SINGLE_1);
        assertTrue(roomBook.hasRoom(SINGLE_1.getRoomID()));
    }

    @Test
    public void hasRoom_roomWithSameIdPrice_returnsTrue() {
        roomBook.addRoom(DOUBLE_1);
        Room editedDouble1 = new RoomBuilder(SINGLE_1)
                .withRoomID(DOUBLE_1.getRoomID())
                .withPrice(DOUBLE_1.getPrice())
                .build();
        assertTrue(roomBook.hasRoom(editedDouble1.getRoomID()));
    }
    @Test
    public void getRoom_roomNotInRoomBook_returnsEmptyRoom() {
        Room expectedOutput = UniqueRoomList.EMPTY_ROOM;
        Room actualOutput = roomBook.getRoom(2103);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void getRoom_roomInRoomBook_returnsCorrectRoom() {
        roomBook.addRoom(DEFAULT_ROOM);
        Room actualOutput = roomBook.getRoom(DEFAULT_ROOMID);

        assertEquals(DEFAULT_ROOM, actualOutput);
    }

    @Test
    public void getAvailableRooms_success() {
        UniqueRoomList internal = new UniqueRoomList();
        internal.add(SINGLE_1);
        internal.add(DOUBLE_1);
        internal.add(SUITE_1);

        ObservableList<Integer> curr = FXCollections.observableArrayList(SINGLE_1.getRoomID());
        ObservableList<Integer> expectedOutput = internal.getComplementRooms(curr);

        roomBook.addRoom(SINGLE_1);
        roomBook.addRoom(DOUBLE_1);
        roomBook.addRoom(SUITE_1);
        assertEquals(expectedOutput, roomBook.getAvailableRooms(curr));
    }

    @Test
    public void getRoomList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> roomBook.getRoomList().remove(0));
    }

    /**
     * A stub ReadOnlyPersonBook whose persons list can violate interface constraints.
     */
    private static class RoomBookStub implements ReadOnlyRoomBook {
        private final ObservableList<Room> rooms = FXCollections.observableArrayList();

        RoomBookStub(Collection<Room> rooms) {
            this.rooms.setAll(rooms);
        }

        @Override
        public ObservableList<Room> getRoomList() {
            return rooms;
        }
    }
}
