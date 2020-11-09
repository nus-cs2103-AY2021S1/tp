package seedu.address.model;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_DAN;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalRoomService;


class RoomServiceBookTest {
    private final RoomServiceBook roomServiceBook = new RoomServiceBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), roomServiceBook.getRoomServiceList());
    }

    @Test
    void addRoomService_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomServiceBook.addRoomService(null));
    }

    @Test
    void addRoomService_success() {
        roomServiceBook.addRoomService(TypicalRoomService.ROOM_SERVICE_DAN_DINING);
        assertEquals(roomServiceBook.getRoomServiceList().size(), 1);
    }

    @Test
    void getRoomServicesForBooking_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomServiceBook.getRoomServicesForBooking(null));
    }

    @Test
    void getRoomServicesForBooking_success() {
        roomServiceBook.addRoomService(TypicalRoomService.ROOM_SERVICE_DAN_DINING);
        assertTrue(roomServiceBook.getRoomServicesForBooking(
                VALID_BOOKING_ID_DAN).get(0).equals(TypicalRoomService.ROOM_SERVICE_DAN_DINING));
        assertEquals(roomServiceBook.getRoomServicesForBooking(VALID_BOOKING_ID_AMY).size(), 0);
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomServiceBook.resetData(null));
    }

    @Test
    void resetData_withValidReadOnlyRoomServiceBook_replacesData() {
        RoomServiceBook newData = TypicalRoomService.getTypicalRoomServiceBook();
        roomServiceBook.resetData(newData);
        assertEquals(newData, roomServiceBook);
    }

    @Test
    void getRoomServiceList() {
        roomServiceBook.addRoomService(TypicalRoomService.ROOM_SERVICE_DAN_DINING);
        roomServiceBook.addRoomService(TypicalRoomService.ROOM_SERVICE_DAN_WIFI);
        roomServiceBook.addRoomService(TypicalRoomService.ROOM_SERVICE_DAN_MASSAGE);
        assertEquals(roomServiceBook.getRoomServiceList().size(), 3);
    }

    @Test
    void testEquals() {
        assertTrue(roomServiceBook.equals(new RoomServiceBook()));

        assertFalse(roomServiceBook.equals(null));

        assertFalse(roomServiceBook.equals(6));
    }
}
