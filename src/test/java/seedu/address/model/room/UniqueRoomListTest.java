package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.ROOM_PATIENT_ALICE_NO_TASK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class UniqueRoomListTest {

    private final UniqueRoomList uniqueRoomList = new UniqueRoomList();

    @Test
    public void addRooms_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.addRooms(null));
    }

    @Test
    public void addRooms_roomInList_returnsTrue() {
        uniqueRoomList.addRooms(ROOM_PATIENT_ALICE_NO_TASK);
        assertTrue(uniqueRoomList.containsRoom(ROOM_PATIENT_ALICE_NO_TASK));
    }

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), uniqueRoomList.getRoomObservableList());
    }

    @Test
    public void addRooms_numOfRooms_returnsTrue() {
        uniqueRoomList.addRooms(5);
        assertEquals(uniqueRoomList.getNumOfRooms(), 5);
    }

    @Test
    public void addRoom_returnTrue() {
        uniqueRoomList.addRooms(5);
        assertEquals(uniqueRoomList.getNumOfRooms(), 5);
        uniqueRoomList.addRooms(3);
        assertEquals(uniqueRoomList.getNumOfRooms(), 3);
        uniqueRoomList.addRooms(10);
        assertEquals(uniqueRoomList.getNumOfRooms(), 10);
    }

    @Test
    public void test_multipleAddRoom_returnTrue() {
        uniqueRoomList.addRooms(ROOM_PATIENT_ALICE_NO_TASK);
        uniqueRoomList.addRooms(5);
        assertEquals(uniqueRoomList.getNumOfRooms(), 5);
        uniqueRoomList.addRooms(3);
        assertEquals(uniqueRoomList.getNumOfRooms(), 3);
        uniqueRoomList.addRooms(10);
        assertEquals(uniqueRoomList.getNumOfRooms(), 10);
    }

    @Test
    public void test_roomOccupied_returnTrue() {
        uniqueRoomList.addRooms(ROOM_PATIENT_ALICE_NO_TASK);
        uniqueRoomList.addRooms(5);
        assertTrue(uniqueRoomList.containsRoom(ROOM_PATIENT_ALICE_NO_TASK));
        uniqueRoomList.addRooms(3);
        assertTrue(uniqueRoomList.containsRoom(ROOM_PATIENT_ALICE_NO_TASK));
        uniqueRoomList.addRooms(10);
        assertTrue(uniqueRoomList.containsRoom(ROOM_PATIENT_ALICE_NO_TASK));
    }

    @Test
    public void addRooms_occupiedRooms_returnsTrue() {
        uniqueRoomList.addRooms(ROOM_PATIENT_ALICE_NO_TASK);
        uniqueRoomList.addRooms(5);
        assertTrue(uniqueRoomList.containsRoom(ROOM_PATIENT_ALICE_NO_TASK));
        uniqueRoomList.addRooms(3);
        assertTrue(uniqueRoomList.containsRoom(ROOM_PATIENT_ALICE_NO_TASK));
        uniqueRoomList.addRooms(10);
        assertTrue(uniqueRoomList.containsRoom(ROOM_PATIENT_ALICE_NO_TASK));
    }

    @Test
    public void addRooms_listOfOccupiedRooms_returnsTrue() {
        List<Room> expectedOccupiedRooms = new ArrayList<>();
        uniqueRoomList.addRooms(10);
        IntStream.iterate(1, x -> x <= 10, x -> x + 2).forEach(x -> {
            if (x >= 5) {
                expectedOccupiedRooms.add(new Room(x, true));
            }
            uniqueRoomList.getRoomObservableList().get(x - 1).setOccupied(true);
        });
        uniqueRoomList.setPreferredNumOfRooms(4);
        List<Room> actualOccupiedRooms = uniqueRoomList.occupiedRooms();
        System.out.println(actualOccupiedRooms.get(0).equals(expectedOccupiedRooms.get(0)));
        assertEquals(actualOccupiedRooms, expectedOccupiedRooms);
    }

    @Test
    public void addRooms_listOfUnoccupiedRooms_returnsTrue() {
        List<Room> expectedUnoccupiedRooms = new ArrayList<>();
        uniqueRoomList.addRooms(10);
        IntStream.iterate(1, x -> x <= 10, x -> x + 2).forEach(x -> {
            if (x < 4) {
                expectedUnoccupiedRooms.add(new Room(x + 1));
            }
            uniqueRoomList.getRoomObservableList().get(x - 1).setOccupied(true);
        });
        uniqueRoomList.setPreferredNumOfRooms(4);
        List<Room> actualOccupiedRooms = uniqueRoomList.unOccupiedRooms();
        System.out.println(actualOccupiedRooms.get(0).equals(expectedUnoccupiedRooms.get(0)));
        assertEquals(actualOccupiedRooms, expectedUnoccupiedRooms);
    }

    @Test
    public void addRooms_numOfOccupiedRooms_returnsTrue() {
        uniqueRoomList.addRooms(10);
        IntStream.iterate(1, x -> x <= 10, x -> x + 2).forEach(x ->
                uniqueRoomList.getRoomObservableList().get(x - 1).setOccupied(true));
        uniqueRoomList.setPreferredNumOfRooms(5);
        assertEquals(2, uniqueRoomList.getNumOfExcessOccupiedRooms());
    }

    @Test
    public void addRooms_numOfUnoccupiedRooms_returnsTrue() {
        uniqueRoomList.addRooms(10);
        IntStream.iterate(1, x -> x <= 10, x -> x + 2).forEach(x ->
                uniqueRoomList.getRoomObservableList().get(x - 1).setOccupied(true));
        uniqueRoomList.setPreferredNumOfRooms(5);
        assertEquals(2, uniqueRoomList.numOfEmptyRooms());
    }

    @Test
    void testPriorityQueueEquals() {
        UniqueRoomList roomList = new UniqueRoomList();
        PriorityQueue<Room> rooms1 = new PriorityQueue<>(new ComparableRoom());
        PriorityQueue<Room> rooms2 = new PriorityQueue<>(new ComparableRoom());

        //same PriorityQueue -> returns true
        assertTrue(roomList.equals(rooms1, rooms1));

        //2 empty PriorityQueue of rooms -> returns true
        assertTrue(roomList.equals(rooms1, rooms2));

        for (int i = 0; i < 10; i++) {
            Room room = new Room(i);
            rooms1.add(room);
            rooms2.add(room);
        }


        //2 PriorityQueue of rooms with same content -> returns true
        assertTrue(roomList.equals(rooms1, rooms2));

        for (int i = 0; i < 10; i++) {
            Room room = new Room(i);
            rooms1.add(room);
            rooms2.add(room);
        }

        Room room = rooms2.poll();

        //2 PriorityQueue of different size -> returns false
        assertFalse(roomList.equals(rooms1, rooms2));

        for (int i = 0; i < 10; i++) {
            Room r = new Room(i);
            rooms1.add(r);
            rooms2.add(r);
        }
        rooms2.add(room);
        rooms1.poll();
        rooms1.add(new Room(100));
        //2 PriorityQueue of different content -> returns false
        assertFalse(roomList.equals(rooms1, rooms2));

    }
}
