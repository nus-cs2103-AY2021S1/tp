package seedu.address.model.roomservice;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_DAN;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RoomServiceBuilder;
import seedu.address.testutil.TypicalRoomService;

class RoomServiceTest {

    @Test
    void belongsToBooking() {
        assertTrue(TypicalRoomService.ROOM_SERVICE_DAN_DINING.belongsToBooking(VALID_BOOKING_ID_DAN));
        assertFalse(TypicalRoomService.ROOM_SERVICE_DAN_DINING.belongsToBooking(VALID_BOOKING_ID_AMY));
    }

    @Test
    void getBookingId() {
        assertTrue(TypicalRoomService.ROOM_SERVICE_DAN_DINING.getBookingId().equals(VALID_BOOKING_ID_DAN));
        assertFalse(TypicalRoomService.ROOM_SERVICE_DAN_DINING.getBookingId().equals(VALID_BOOKING_ID_AMY));
    }

    @Test
    void testEquals() {
        RoomService defaultRoomService = new RoomServiceBuilder().build();
        // same value -> return true
        RoomService copy = new RoomServiceBuilder(defaultRoomService).build();
        assertTrue(defaultRoomService.equals(copy));

        // same object -> return true
        assertTrue(defaultRoomService.equals(defaultRoomService));

        //null -> return false
        assertFalse(defaultRoomService.equals(null));

        // different type -> returns false
        assertFalse(defaultRoomService.equals(5));

        // different roomService -> return false
        assertFalse((defaultRoomService.equals(TypicalRoomService.ROOM_SERVICE_DAN_DINING)));
    }
}
